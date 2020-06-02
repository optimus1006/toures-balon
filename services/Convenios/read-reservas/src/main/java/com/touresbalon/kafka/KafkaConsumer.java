package com.touresbalon.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.exceptions.ConvenioNotFoundException;
import com.touresbalon.restclient.ConvenioClientService;
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
                    Convenio convenio = convenioClientService.consultarConvenio(reserva.getIdConvenio().toString());
                    if(convenio == null){
                        throw new ConvenioNotFoundException("No se pudo encontrar el convenio con id " + reserva.getIdConvenio().toString());
                    } else {
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
            } catch (JsonProcessingException e) {
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


}
