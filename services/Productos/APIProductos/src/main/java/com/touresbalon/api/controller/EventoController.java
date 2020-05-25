package com.touresbalon.api.controller;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.AsientosPSTRq;
import com.touresbalon.api.domain.AsientosPSTRs;
import com.touresbalon.api.domain.Error;
import com.touresbalon.api.domain.EventoException;
import com.touresbalon.api.domain.EventosGETAllRS;
import com.touresbalon.api.domain.EventosGETByIdRs;
import com.touresbalon.api.domain.EventosPCTRq;
import com.touresbalon.api.domain.EventosPCTRs;
import com.touresbalon.api.domain.EventosPSTRq;
import com.touresbalon.api.domain.EventosPSTRs;
import com.touresbalon.api.domain.LocalidadesPSTRq;
import com.touresbalon.api.domain.LocalidadesPSTRs;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.service.AsientoService;
import com.touresbalon.api.service.EventoService;
import com.touresbalon.api.service.LocalidadService;

import io.swagger.annotations.ApiOperation;

@Path("/productos/eventos")
public class EventoController {

	@Inject
	EventoService eventoService;
	
	@Inject
	LocalidadService localidadService;
	
	@Inject
	AsientoService asientoService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Consulta al información de los eventos basado en parámetros de búsqueda", nickname = "eventosGETAll", notes = "Consulta al información de los eventos basado en parámetros de búsqueda ", response = EventosGETAllRS.class, tags={ "Eventos", })
	public Response eventosGETAll() {
		EventosGETAllRS eventosGETAllRS = new EventosGETAllRS();
		try {
			eventosGETAllRS.setEventos(eventoService.listarEventos());
        	return Response.status(Response.Status.OK).entity(eventosGETAllRS).type(MediaType.APPLICATION_JSON).build();
    	}catch (EventoException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Consulta al información de un evento por su id", nickname = "eventosGETById", notes = "Consulta al información de un evento por su id ", response = EventosGETByIdRs.class, tags={ "Eventos", })
	@Path("/{idEvento}")
	public Response eventosGETById(@PathParam(value = "idEvento") Long codigo) {
		EventosGETByIdRs response = new EventosGETByIdRs();
		try {
    		response.setEvento(eventoService.consultarPorId(codigo));
        	return Response.status(Response.Status.OK).entity(response).type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PATCH
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{idEvento}")
	@ApiOperation(value = "Actualizar información del evento", nickname = "eventosPCT", notes = "Actualiza el evento de acuerdo con los parámetros recibidos.", response = EventosPCTRs.class, tags={ "Eventos", })
	public Response eventosPCT(@PathParam(value = "idEvento") Long codigo, @RequestBody EventosPCTRq eventosPCTRq) {
		EventosPCTRs eventosPCTRs = new EventosPCTRs();
		try {
			eventosPCTRs.setEvento(eventoService.actualizar(eventosPCTRq.getEvento(), codigo));
        	return Response.status(Response.Status.ACCEPTED).entity(eventosPCTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Registrar un nuevo evento", nickname = "eventosPST", notes = "Crea el evento de acuerdo con los parámetros recibidos.", response = EventosPSTRs.class, tags={ "Eventos", })
	public Response eventosPST(@RequestBody EventosPSTRq eventosPSTRq) {
		EventosPSTRs eventosPSTRs = new EventosPSTRs();
		try {
			eventosPSTRs.setEvento(eventoService.crearEvento(eventosPSTRq.getEvento()));
        	return Response.status(Response.Status.ACCEPTED).entity(eventosPSTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{idEvento}/localidades")
	@ApiOperation(value = "Crear una localidad de acuerdo a un evento", nickname = "localidadesPST", notes = "crear una localidad de acuerdo a al codigo de un evento", response = LocalidadesPSTRs.class, tags={ "Eventos", })
	public Response localidadesPST(@PathParam(value = "idEvento") Long codigo, @RequestBody LocalidadesPSTRq localidadesPSTRq) {
		LocalidadesPSTRs localidadesPSTRs = new LocalidadesPSTRs();
		try {
			localidadesPSTRs.setLocalidad(localidadService.crearLocalidad(localidadesPSTRq.getLocalidad(), codigo));
        	return Response.status(Response.Status.ACCEPTED).entity(localidadesPSTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/localidades/{idLocalidad}/asientos")
	@ApiOperation(value = "Crear reservar un asiento de un evento en una localidad", nickname = "asientosLocalidadPST", notes = "Crear reservar un asiento de un evento en una localidad", response = AsientosPSTRs.class, tags={ "Eventos", })
	public Response asientosLocalidadPST(@PathParam(value = "idLocalidad") Long idLocalidad, @RequestBody AsientosPSTRq asientosPSTRq) {
		AsientosPSTRs asientosPSTRs = new AsientosPSTRs();
		try {
			Asiento asiento = new Asiento();
			asiento.setId(asientoService.reservarAsientoEvento(asientosPSTRq.getAsiento(), idLocalidad));
			asientosPSTRs.setAsiento(asiento);
        	return Response.status(Response.Status.ACCEPTED).entity(asientosPSTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/localidades/{idLocalidad}/asientos/{idAsiento}")
	@ApiOperation(value = "cancelar la reserva de un asiento de acuerdo a una localidad y un evento", nickname = "asientosLocalidadPTC", notes = "cancelar la reserva de un asiento de acuerdo a una localidad y un evento", tags={ "Eventos", })
	public Response asientoDEl(@PathParam(value = "idLocalidad") Long idLocalidad,@PathParam(value = "idAsiento") Long idAsiento) {
		try {
			asientoService.borrarAsiento(idLocalidad, idAsiento);
        	return Response.status(Response.Status.ACCEPTED).entity("").type(MediaType.APPLICATION_JSON).build();
    	}catch (TransporteException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
}
