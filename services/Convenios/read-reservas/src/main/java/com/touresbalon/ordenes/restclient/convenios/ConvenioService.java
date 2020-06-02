package com.touresbalon.ordenes.restclient.convenios;

import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.ConvenioGETByIdRs;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("api/convenios")
@RegisterRestClient(configKey = "convenios-api")
public interface ConvenioService {

    @GET
    @Path("/{identificacion}")
    @Produces("application/json")
    Convenio consultarConvenio (@PathParam String identificacion);

}
