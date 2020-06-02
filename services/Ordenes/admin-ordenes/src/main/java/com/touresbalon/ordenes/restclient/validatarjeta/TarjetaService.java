package com.touresbalon.ordenes.restclient.validatarjeta;

import com.touresbalon.ordenes.restclient.validatarjeta.model.AprobacionCompra;
import com.touresbalon.ordenes.restclient.validatarjeta.model.CompraValidacion;
import com.touresbalon.ordenes.restclient.validatarjeta.model.RespuestaValidacion;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/tarjeta")
@RegisterRestClient(configKey="validatarjeta-api")
public interface TarjetaService {


    @POST
    @Path("/validar")
    @Produces("application/json")
    RespuestaValidacion validarTarjeta(@RequestBody CompraValidacion compraValidacion);


    @POST
    @Path("/aprobarpago")
    @Produces("application/json")
    RespuestaValidacion aprobarPago(@RequestBody AprobacionCompra aprobacionCompra);
}
