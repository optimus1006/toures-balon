package com.touresbalon.api.kafka;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.api.domain.ProductosPSTRs;
import com.touresbalon.api.service.ProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;

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
    ProductoService productoService;
    
    private io.vertx.kafka.client.consumer.KafkaConsumer<String, String> consumer;
    
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
        startConsumer();
    }
    
    private void startConsumer(){
        // Config values can be moved to application.properties
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "10.0.1.153:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "OrdenCreada");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        // use consumer for interacting with Apache Kafka
        consumer = io.vertx.kafka.client.consumer.KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            LOGGER.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + servers +record.offset());

            try {
                ProductoMessage producto = objectMapper.readValue(record.value(), ProductoMessage.class);

                /*Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
                WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
                ((WorkerExecutor) executor).executeBlocking(promise -> {
                    ordenService.realizarCompra(orden);
                }, res -> {
                    System.out.println("The result is: " + res.result());
                });*/
                vertx.executeBlocking(promise -> {
//                    if(producto.getAccion().equals(EnumProductoAction.CREARRESERVA)) {
                	ProductosPSTRs productoResponse = new ProductosPSTRs();
                	try {
                		productoResponse=productoService.crearProducto(producto.getProducto());
                    	System.out.println(productoResponse.getProducto().getId());
                	}
                	catch (Exception e) {
                		System.out.println("Message"+e.getMessage());
                		System.out.println("Cause"+e.getCause());
                		System.out.println("Trace"+e.getStackTrace());
					}
                	
//                    }
//                    else if(orden.getAccion().equals(EnumOrderAction.ACTUALIZACION)) {
//                        ordenService.actualizarOrden(orden, orden.getCodigo());
//                    } else if(orden.getAccion().equals(EnumOrderAction.CREACION)) {
//                        ordenService.crearOrden(orden);
//                    }
                    promise.complete(productoResponse.getProducto().getId());
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
        consumer.subscribe("OrdenCreada", ar -> {
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
