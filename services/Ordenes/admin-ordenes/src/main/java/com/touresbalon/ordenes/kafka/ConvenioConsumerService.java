package com.touresbalon.ordenes.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.ordenes.restclient.productos.model.Producto;
import com.touresbalon.ordenes.service.OrdenService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ConvenioConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvenioConsumerService.class);

    @Inject
    Vertx vertx;

    @ConfigProperty(name = "kafka.bootstrap.servers")
    String servers;

    @ConfigProperty(name = "kafka.incoming.topic")
    String topic;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    OrdenService ordenService;

    private KafkaConsumer<String, String> consumer;

    private static Map<String, String> config;

    static {
        config = new HashMap<>();
        // Config values can be moved to application.properties.aws.aws
        //config.put("bootstrap.servers", "localhost:9092");
        //config.put("bootstrap.servers", "ip-10-0-1-153.us-east-2.compute.internal:19092");
        config.put("bootstrap.servers", "b-1.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092,b-2.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092");
        //config.put("bootstrap.servers", "ec2-18-219-44-85.us-east-2.compute.amazonaws.com:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "group_reservas");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");
    }
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
        startConsumerConvenios();
    }

    private void startConsumerConvenios() {
        // use consumer for interacting with Apache Kafka
        consumer = KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            LOGGER.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + servers +record.offset());

            try {
                AprobacionReserva aprobacionReserva = objectMapper.readValue(record.value(), AprobacionReserva.class);
                vertx.executeBlocking(promise -> {
                    ordenService.validarReserva(aprobacionReserva);
                    promise.complete(aprobacionReserva.getCodigoAprobacion());
                }, res -> {
                    LOGGER.info("The result is: " + res.result());
                });
            } catch (JsonProcessingException e) {
                LOGGER.error("Error JsonProcessingException= " , e);
            } catch (Exception e) {
                LOGGER.error("Error cargando items = " , e);
            }
            consumer.commit(ar -> {

                if (ar.succeeded()) {
                    LOGGER.info("Last read message offset committed");
                } else {
                    LOGGER.error("Error = " , ar.cause());
                }
            });
        });
        configConsumer("ReservaRealizada");
    }

    private void configConsumer(String topic) {
        consumer.exceptionHandler(e -> {
            LOGGER.error("Error en  = " + topic , e);
            consumer.close();
        });
        // or just subscribe to a single topic
        consumer.subscribe(topic, ar -> {
            if (ar.succeeded()) {
                LOGGER.info("subscribed " +topic);
            } else {
                LOGGER.error("Could not subscribe " + topic , ar.cause());
            }
        });

    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("onStop");
        consumer.close();
    }

}
