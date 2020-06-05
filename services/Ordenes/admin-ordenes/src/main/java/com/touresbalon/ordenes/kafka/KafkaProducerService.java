package com.touresbalon.ordenes.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.ordenes.api.model.Orden;
import com.touresbalon.ordenes.exceptions.KafkaException;
import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRq;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Inject
    Vertx vertx;

    private KafkaProducer<String, String> producer;

    private KafkaProducer<String, String> producerRead;

    @Inject
    ObjectMapper objectMapper;

    static String servers;

    private static Map<String, String> config;

    static {
        config = new HashMap<>();
        // Config values can be moved to application.properties.aws.aws
        //config.put("bootstrap.servers", "localhost:9092");
        config.put("bootstrap.servers", "b-1.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092,b-2.touresbalonkafka.3inpqc.c4.kafka.us-east-2.amazonaws.com:9092");
        //config.put("bootstrap.servers", "ip-10-0-1-153.us-east-2.compute.internal:19092");
        //config.put("bootstrap.servers", "ec2-18-219-44-85.us-east-2.compute.amazonaws.com:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");
    }

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("onStart");
    }

    public void sendOrderToKafka(OrdenMessage orden) throws KafkaException {
        try {
            // ignore the null fields globally
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(
                    "read-order", orden.getCodigo().toString(),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orden));
            //sendMessageToKafka(record);

            producerRead = KafkaProducer.create(vertx, config);
            producerRead.exceptionHandler(e -> {
                LOGGER.error("Error en envio a kafka  ", e);
                //producerRead.close();
                throw new KafkaException(e);
            });
            producerRead.send(record, done -> {
                if (done.succeeded()) {
                    RecordMetadata recordMetadata = done.result();
                    LOGGER.info("Message " + record + " written on topic=" + recordMetadata.getTopic() +
                            ", partition=" + recordMetadata.getPartition() +
                            ", offset=" + recordMetadata.getOffset());
                } /*else {
                LOGGER.error("Error en envio a kafka ", done.cause());
                throw new KafkaException(done.cause());
            }*/
            });
        } catch (
                JsonProcessingException e) {
            LOGGER.error("Error en serializacion ", e);
            throw new KafkaException(e);
        }
    }

    public void sendProductsToKafka(ProductosPSTRq productosPSTRq) throws KafkaException {
        try {
            // ignore the null fields globally
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(
                    "OrdenCreada", productosPSTRq.getProducto().getIdOrden() + "",
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productosPSTRq));
            sendMessageToKafka(record);
        } catch (
                JsonProcessingException e) {
            LOGGER.error("Error en serializacion ", e);
            throw new KafkaException(e);
        }
    }

    private void sendMessageToKafka(KafkaProducerRecord<String, String> record) throws KafkaException {
        // use producer for interacting with Apache Kafka
        producer = KafkaProducer.create(vertx, config);
        producer.exceptionHandler(e -> {
            LOGGER.error("Error en envio a kafka  ", e);
            //producer.close();
            throw new KafkaException(e);
        });
        producer.send(record, done -> {
            if (done.succeeded()) {
                RecordMetadata recordMetadata = done.result();
                LOGGER.info("Message " + record + " written on topic=" + recordMetadata.getTopic() +
                        ", partition=" + recordMetadata.getPartition() +
                        ", offset=" + recordMetadata.getOffset());
            } /*else {
                LOGGER.error("Error en envio a kafka ", done.cause());
                throw new KafkaException(done.cause());
            }*/
        });
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("onStop");
        //producer.close();
        //vertx.close();
    }

}
