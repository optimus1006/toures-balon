package com.touresbalon.convenios.service;

import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.ConvenioGETByIdRs;
import com.touresbalon.ordenes.kafka.ConvenioMessage;
import com.touresbalon.ordenes.kafka.KafkaProducerService;
import com.touresbalon.ordenes.restclient.convenios.model.ConveniosGETByIdRs;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ConvenioClientService {
    private static final Logger log = LoggerFactory.getLogger(ConvenioClientService.class);

    @Inject
    @RestClient
    com.touresbalon.ordenes.restclient.convenios.ConvenioService convenioService;

    @Inject
    KafkaProducerService kafkaProducerService;

    /**
     * Realiza consulta del convenio
     *
     * @param identificacion
     * @return Convenio si resulta exitoso
     */
    public Convenio consultarConvenio(String identificacion) {
        log.info("consultarConvenio(String identificacion) ");
        Convenio convenioGETByIdRs = null;

        try {
            convenioGETByIdRs = convenioService.consultarConvenio(identificacion);
            ConvenioMessage convenioMessage = new ConvenioMessage();

            convenioMessage.setIdentificacion(convenioGETByIdRs.getIdentificacion());
            convenioMessage.setEndpoint(convenioGETByIdRs.getEndpoint());
            convenioMessage.setTemplateEntrada(convenioGETByIdRs.getTemplateEntrada());
            convenioMessage.setTemplateSalida(convenioGETByIdRs.getTemplateSalida());

            kafkaProducerService.sendOfertaToKafka(convenioMessage);

        } catch (Exception e){
            log.error("Error en la consulta del convenio.", e);
        }

        return convenioGETByIdRs;
    }

}
