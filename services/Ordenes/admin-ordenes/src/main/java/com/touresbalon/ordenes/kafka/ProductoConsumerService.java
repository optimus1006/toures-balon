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
public class ProductoConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoConsumerService.class);

    @Inject
    Vertx vertx;

    String servers;

    @ConfigProperty(name = "kafka.incoming.topic.products")
    String topic;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    OrdenService ordenService;

    private KafkaConsumer<String, String> consumer;

    private static Map<String, String> config;

    static {
        config = new HashMap<>();
        // Config values can be moved to application.properties
        config.put("bootstrap.servers", "ec2-3-16-57-65.us-east-2.compute.amazonaws.com:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "group_products");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");
    }
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
        startConsumerProducts();
    }


    private void startConsumerProducts(){
        // use consumer for interacting with Apache Kafka
        consumer = KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            LOGGER.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + servers +record.offset());

            try {
                ReservaMessage reservaMessage = objectMapper.readValue(record.value(), ReservaMessage.class);
                vertx.executeBlocking(promise -> {
                    ordenService.actualizarOrdenItems(reservaMessage);
                    promise.complete(reservaMessage.getIdProductoDetalle());
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
            }
        });
        configConsumer("RealizarReserva");
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
