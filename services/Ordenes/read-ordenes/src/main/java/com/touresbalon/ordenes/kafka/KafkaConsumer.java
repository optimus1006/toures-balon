package com.touresbalon.ordenes.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.ordenes.api.model.Orden;
import com.touresbalon.ordenes.service.OrdenService;
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
    OrdenService ordenService;

    private io.vertx.kafka.client.consumer.KafkaConsumer<String, String> consumer;


    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
        startConsumer();
    }

    private void startConsumer(){
        // Config values can be moved to application.properties
        Map<String, String> config = new HashMap<>();
		//config.put("bootstrap.servers", "ip-10-0-1-153.us-east-2.compute.internal:19092");
		config.put("bootstrap.servers", "b-2.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092,b-1.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092");
        //config.put("bootstrap.servers", "ec2-3-16-57-65.us-east-2.compute.amazonaws.com:9092");
        //config.put("bootstrap.servers", "localhost:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "group_orders");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        // use consumer for interacting with Apache Kafka
        consumer = io.vertx.kafka.client.consumer.KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            LOGGER.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + servers +record.offset());

            try {
                OrdenMessage orden = objectMapper.readValue(record.value(), OrdenMessage.class);

                /*Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
                WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
                ((WorkerExecutor) executor).executeBlocking(promise -> {
                    ordenService.realizarCompra(orden);
                }, res -> {
                    System.out.println("The result is: " + res.result());
                });*/
                vertx.executeBlocking(promise -> {
                    if(orden.getAccion().equals(EnumOrderAction.PAGO)) {
                        ordenService.realizarCompra(orden);
                    } else if(orden.getAccion().equals(EnumOrderAction.ACTUALIZACION)) {
                        ordenService.actualizarOrden(orden, orden.getCodigo());
                    } else if(orden.getAccion().equals(EnumOrderAction.CREACION)) {
                        ordenService.crearOrden(orden);
                    } else if(orden.getAccion().equals(EnumOrderAction.AGREGAR_ITEM)) {
                        ordenService.agregarItem(orden);
                    }
                    promise.complete(orden.getCodigo());
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
        consumer.exceptionHandler(e -> {
            LOGGER.error("Error = " , e);
            consumer.close();
        });
        // or just subscribe to a single topic
        consumer.subscribe("read-order", ar -> {
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
