package com.tourebalon.mock;

import com.tourebalon.mock.model.EnumRespuestaIngreso;
import com.tourebalon.mock.model.IngresoCompra;
import com.tourebalon.mock.model.RespuestaIngreso;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Random;

@Path("/arm")
public class ARMResource {

    @POST
    @Path("/ingresarpago")
    @Produces("application/json")
    public RespuestaIngreso ingresarPago(@RequestBody IngresoCompra ingresoCompra) {
        RespuestaIngreso respuesta = new RespuestaIngreso();
        respuesta.setCodigo(EnumRespuestaIngreso.RESPUESTA_EXITOSA.getCodigo());
        respuesta.setMensaje(EnumRespuestaIngreso.RESPUESTA_EXITOSA.name());
        respuesta.setUidPago(new Random().nextLong() + "");
        return respuesta;
    }


}
