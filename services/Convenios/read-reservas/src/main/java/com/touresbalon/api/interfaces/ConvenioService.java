package com.touresbalon.api.interfaces;

import com.touresbalon.api.domain.Convenio;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("api/convenios")
@RegisterRestClient(configKey = "convenios-api")
public interface ConvenioService {

    @GET
    @Path("/{identificacion}")
    @Produces("application/json")
    Convenio consultarConvenio (@PathParam String identificacion);

}
