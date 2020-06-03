package com.touresbalon.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.api.domain.AprobacionReserva;
import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.RouteMessageGETRq;
import com.touresbalon.api.exceptions.ConvenioNotFoundException;
import com.touresbalon.restclient.ConvenioClientService;
import com.touresbalon.restclient.RouteMessageClientService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@ApplicationScoped
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Inject
    Vertx vertx;

    @ConfigProperty(name = "kafka.bootstrap.servers")
    String servers;

    @ConfigProperty(name = "kafka.incoming.topic")
    String topic;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ConvenioClientService convenioClientService;

    @Inject
    RouteMessageClientService routeMessageClientService;

    @Inject
    KafkaProducerService kafkaProducerService;

    private io.vertx.kafka.client.consumer.KafkaConsumer<String, String> consumer;


    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
        startConsumer();
    }

    private void startConsumer(){
        // Config values can be moved to application.properties
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "RealizarReserva");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        // use consumer for interacting with Apache Kafka
        consumer = io.vertx.kafka.client.consumer.KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            LOGGER.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + servers +record.offset());

            try {
                ReservaMessage reserva = objectMapper.readValue(record.value(), ReservaMessage.class);

                vertx.executeBlocking(promise -> {
                    //TODO: Logica para consultar al convenio para ver si existe
                    LOGGER.info("Sending request to /api/convenios on ");
                    Convenio convenio = convenioClientService.consultarConvenio(reserva.getIdConvenio());
                    if(convenio == null){
                        LOGGER.info("Convenio not found");
                        throw new ConvenioNotFoundException("No se pudo encontrar el convenio con id " + reserva.getIdConvenio().toString());
                    } else {
                        //Envío la data capturada hacia el homologador:
                        RouteMessageGETRq routeMessageGETRq = new RouteMessageGETRq();
                        LOGGER.info("Preparing the message routeMessageGERTRq to be sent");
                        ConvenioMessage convenioMessage = new ConvenioMessage();
                        convenioMessage.setEndpoint(convenio.getEndpoint());
                        convenioMessage.setTemplateEntrada(convenio.getTemplateEntrada());
                        convenioMessage.setTemplateSalida(convenio.getTemplateSalida());
                        routeMessageGETRq.setConvenioMessage(convenioMessage);
                        routeMessageGETRq.setReservaMessage(reserva);
                        LOGGER.info("Consuming the service enviarReserva");

                        //Mock
                        AprobacionReserva aprobacion = procesarMock(routeMessageGETRq);
                        //Servicio real
                        //AprobacionReserva aprobacion = routeMessageClientService.enviarReserva(routeMessageGETRq);

                        if(aprobacion != null){
                            LOGGER.info("Object Aprobacion received, sending to producer");
                            kafkaProducerService.sendAprobacionToKafka(aprobacion);
                        } else {
                            LOGGER.info("For some reason, the Aprobacion response was null?");
                        }
                        promise.complete(reserva.toString());
                    }
                }, res -> {
                    LOGGER.info("The result is: " + res.result());
                });
                consumer.commit(ar -> {

                    if (ar.succeeded()) {
                        LOGGER.info("Last read message offset committed");
                    } else {
                        LOGGER.error("Error = " , ar.cause());
                    }
                });
            } catch (Exception e) {
                LOGGER.error("Error JsonProcessingException= " , e);

                vertx.executeBlocking(promise -> {
                    //TODO: Logica para reportar error en topico de respuesta

                    promise.complete("{Error en procesamiento del mensaje on ID [PONER_UN_ID_AQUI]]}");
                }, res -> {
                    LOGGER.info("Exception handling...");
                    LOGGER.info("The result is: " + res.result());
                });
                consumer.commit(ar -> {

                    if (ar.succeeded()) {
                        LOGGER.info("Last read message offset committed");
                    } else {
                        LOGGER.error("Error = " , ar.cause());
                    }
                });
            }
        });
        consumer.exceptionHandler(e -> {
            LOGGER.error("Error = " , e);
            consumer.close();
        });
        // or just subscribe to a single topic
        consumer.subscribe("RealizarReserva", ar -> {
            if (ar.succeeded()) {
                LOGGER.info("subscribed");
            } else {
                LOGGER.error("Could not subscribe " , ar.cause());
            }
        });
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("onStop");
        consumer.close();
    }

    private static int getRandomNumber(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private AprobacionReserva procesarMock(RouteMessageGETRq routeMessageGETRq){
        AprobacionReserva aprobacion = new AprobacionReserva();
        try{

            int probabilityOfSuccess = 95;
            int currentProbability = getRandomNumber(0, 100);
            if(probabilityOfSuccess <= currentProbability){
                aprobacion.setCodigoAprobacion("0");
                aprobacion.setDetalle("Reserva no aprobada");
            }
            aprobacion.setCodigoExterno(Long.toString(routeMessageGETRq.getReservaMessage().getCodigoExterno()));
            aprobacion.setCodigoExternoDetalle(routeMessageGETRq.getReservaMessage().getCodigoExternoDetalle());
            aprobacion.setDetalle("Reserva aprobada");
            aprobacion.setIdOrden(routeMessageGETRq.getReservaMessage().getIdOrden());
            aprobacion.setIdProducto(Long.valueOf(getRandomNumber(100000, 999999)));
            aprobacion.setIdProductoDetalle(Long.valueOf(getRandomNumber(100000, 999999)));
            aprobacion.setTipoProducto(routeMessageGETRq.getReservaMessage().getTipoProducto().ordinal());//TODO: Validar si este es el valor correcto
            aprobacion.setCodigoAprobacion(String.valueOf(getRandomNumber(10000000, 999999999)));
        } catch (Exception e){
            LOGGER.info("AprobacionReserva: excepcion: ");
            LOGGER.error(e.getMessage(), e);
        }
        return aprobacion;
    }
}
