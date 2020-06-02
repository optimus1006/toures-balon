package com.touresbalon.restclient;

import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.interfaces.ConvenioService;
import com.touresbalon.kafka.ConvenioMessage;
import com.touresbalon.kafka.KafkaProducerService;
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
    ConvenioService convenioService;

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

            //CACA NENE
            //kafkaProducerService.sendOfertaToKafka(convenioMessage);

            //HACIA EL HOMOLOGADOR:


        } catch (Exception e){
            log.error("Error en la consulta del convenio.", e);
        }

        return convenioGETByIdRs;
    }

}
