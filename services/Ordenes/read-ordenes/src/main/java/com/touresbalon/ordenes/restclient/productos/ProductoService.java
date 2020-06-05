package com.touresbalon.ordenes.restclient.productos;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/productos")
@RegisterRestClient(configKey="productos-api")
public interface ProductoService {

    @GET
    @Produces("application/json")
    Response consultarProductoPorId(@QueryParam("idProducto") Long idProducto);
}
