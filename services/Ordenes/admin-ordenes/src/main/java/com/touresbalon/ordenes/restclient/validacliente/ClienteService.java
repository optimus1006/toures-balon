package com.touresbalon.ordenes.restclient.validacliente;

import com.touresbalon.ordenes.restclient.validacliente.model.ClientesGETByIdRs;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/clientes")
@RegisterRestClient(configKey="clientes-api")
public interface ClienteService {


    @GET
    @Path("/{codigo}")
    @Produces("application/json")
    ClientesGETByIdRs validarCliente(@PathParam Long codigo);
}
