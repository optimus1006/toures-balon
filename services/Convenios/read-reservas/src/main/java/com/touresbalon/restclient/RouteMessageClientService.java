package com.touresbalon.restclient;

import com.touresbalon.api.domain.AprobacionReserva;
import com.touresbalon.api.domain.RouteMessageGETRq;
import com.touresbalon.api.interfaces.RouteMessageService;
import com.touresbalon.kafka.KafkaProducerService;
import com.touresbalon.kafka.ReservaMessage;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.xml.ws.Response;

@ApplicationScoped
@Transactional
public class RouteMessageClientService {

    private static final Logger log = LoggerFactory.getLogger(RouteMessageClientService.class);

    @Inject
    @RestClient
    RouteMessageService service;

    @Inject
    KafkaProducerService kafkaProducerService;

    public AprobacionReserva enviarReserva (RouteMessageGETRq routeMessageGETRq) {
        log.info("enviarReserva: Starting service consume...");

        Response response = service.enviarReserva(routeMessageGETRq);
        log.info("enviarReserva: Response was NULL?: " + (response == null?true:false));
        return (AprobacionReserva) response;
    }
}
