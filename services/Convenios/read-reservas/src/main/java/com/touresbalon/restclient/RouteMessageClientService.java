package com.touresbalon.restclient;

import com.touresbalon.api.domain.AprobacionReserva;
import com.touresbalon.api.interfaces.RouteMessageService;
import com.touresbalon.kafka.KafkaProducerService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RouteMessageClientService {

    private static final Logger log = LoggerFactory.getLogger(RouteMessageClientService.class);

    @Inject
    @RestClient
    RouteMessageService service;

    @Inject
    KafkaProducerService kafkaProducerService;

    public AprobacionReserva enviarReserva (AprobacionReserva aprobacionReserva) throws Exception{
        log.info("enviarReserva: Iniciando envío de reserva ");
        return null;//TODO: Deveolver un valor real
    }
}
