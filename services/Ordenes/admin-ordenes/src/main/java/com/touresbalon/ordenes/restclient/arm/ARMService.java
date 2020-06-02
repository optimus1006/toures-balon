package com.touresbalon.ordenes.restclient.arm;

import com.touresbalon.ordenes.restclient.arm.model.IngresoCompra;
import com.touresbalon.ordenes.restclient.arm.model.RespuestaIngreso;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/arm")
@RegisterRestClient(configKey="arm-api")
public interface ARMService {

    @POST
    @Path("/ingresarpago")
    @Produces("application/json")
    RespuestaIngreso ingresarCompra(@RequestBody IngresoCompra ingresoCompra);
}
