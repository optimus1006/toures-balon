package com.touresbalon.ordenes.restclient.productos;

import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRq;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/productos")
@RegisterRestClient(configKey="productos-api")
public interface ProductoService {


    @POST
    @Produces("application/json")
    Response realizarReservas(@RequestBody ProductosPSTRq productosPSTRq);
}
