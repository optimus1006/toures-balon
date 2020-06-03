package com.touresbalon.api.interfaces;

import com.touresbalon.api.domain.AprobacionReserva;
import com.touresbalon.api.domain.RouteMessageGETRq;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("api/routemessage")
@RegisterRestClient(configKey = "routemessage-api")
public interface RouteMessageService {

    @POST
    @Produces("application/json")
    AprobacionReserva enviarReserva (@RequestBody RouteMessageGETRq routeMessageGETRq);

}
