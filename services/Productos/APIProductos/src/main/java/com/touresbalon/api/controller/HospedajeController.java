package com.touresbalon.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.CuartosGETAllRs;
import com.touresbalon.api.domain.CuartosPSTRq;
import com.touresbalon.api.domain.CuartosPSTRs;
import com.touresbalon.api.domain.CuartosPTCRq;
import com.touresbalon.api.domain.CuartosPTCRs;
import com.touresbalon.api.domain.Error;
import com.touresbalon.api.domain.Hospedaje;
import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.domain.HospedajesGetAllRs;
import com.touresbalon.api.domain.HospedajesPCTRq;
import com.touresbalon.api.domain.HospedajesPCTRs;
import com.touresbalon.api.domain.HospedajesPSTRs;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.service.CuartoService;
import com.touresbalon.api.service.HospedajeService;

import io.swagger.annotations.ApiOperation;

@Path("/productos/hospedajes")
public class HospedajeController {

	@Inject
	HospedajeService hospedajeService;
	
	@Inject
	CuartoService cuartoService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	@ApiOperation(value = "", nickname = "hospedajesGetById", notes = "Realiza la consulta de un hospedaje específico, trayendo toda la información del sitio.", response = Hospedaje.class, tags={ "Hospedajes", })
	public Response hospedajesGetById(@PathParam(value = "codigo") Long codigo) {
		Hospedaje response = new Hospedaje();
		try {
    		response=hospedajeService.consultarPorId(codigo);
        	return Response.status(Response.Status.OK).entity(response).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
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
	@ApiOperation(value = "", nickname = "hospedajesGETAll", notes = "Realiza la consulta de un hospedaje específico, trayendo toda la información del sitio.", response = HospedajesGetAllRs.class, tags={ "Hospedajes", })
	public Response hospedajesGetAll(@QueryParam(value= "nombre") String nombre,@QueryParam(value= "calificacion") int calificacion,@QueryParam(value= "tipoHospedaje") String tipoHospedaje,@QueryParam(value= "ciudad") Long ciudad,@QueryParam(value= "convenio") String convenio) {
		HospedajesGetAllRs hospedajesGetAllRs = new HospedajesGetAllRs();
		try {
    		hospedajesGetAllRs.setHospedajes(hospedajeService.consultarHospedajes(nombre, calificacion, tipoHospedaje, ciudad, convenio));
        	return Response.status(Response.Status.OK).entity(hospedajesGetAllRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
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
	@Path("/{codigo}")
	@ApiOperation(value = "Actualizar la información de un hospedaje", nickname = "hospedajesPTC", notes = "Actualizar los datos del hospedaje de acuerdo a los parametros de entrada.", response = HospedajesPCTRs.class, tags={ "Hospedajes", })
	public Response hospedajesPTC(@PathParam(value = "codigo") Long codigo,@RequestBody HospedajesPCTRq hospedajesPCTRq) {
		HospedajesPCTRs hospedajesPCTRs = new HospedajesPCTRs();
		
		try {
			Hospedaje hospedaje = hospedajeService.actualizar(hospedajesPCTRq.getHospedaje(),codigo);
    		hospedajesPCTRs.setHospedaje(hospedaje);
        	return Response.status(Response.Status.OK).entity(hospedajesPCTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
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
	@ApiOperation(value = "Crea un nuevo sitio de hospedaje.", nickname = "hospedajesPST", notes = "Crea un hospedaje dentro del sistema.", response = HospedajesPSTRs.class, tags={ "Hospedajes", })
	public Response hospedajesPST(@RequestBody HospedajesPCTRq hospedajesPCTRq) {
		HospedajesPCTRs hospedajesPCTRs = new HospedajesPCTRs();
		try {
			Hospedaje hospedaje = hospedajeService.crearHospedaje(hospedajesPCTRq.getHospedaje());
    		hospedajesPCTRs.setHospedaje(hospedaje);
        	return Response.status(Response.Status.OK).entity(hospedajesPCTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}/cuartos")
	@ApiOperation(value = "Consultar todos los cuartos existentes en un hospedaje", nickname = "cuartosGETAll", notes = "Consultar todos los cuartos existentes para un hospedaje ", response = CuartosGETAllRs.class, tags={ "Hospedajes", })
	public Response cuartosGETAll(@PathParam(value = "codigo") Long codigo,@QueryParam(value= "idCliente") Long cliente,@QueryParam(value= "idCuerto") Long cuarto) {
		CuartosGETAllRs cuartosGETAllRs = new CuartosGETAllRs();
		try {
			cuartosGETAllRs.setCuartos(cuartoService.listarCuartos(codigo, cliente));
        	return Response.status(Response.Status.OK).entity(cuartosGETAllRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}/cuartos")
	@ApiOperation(value = "Crear un cuarto de acuerdo a un hospedaje", nickname = "cuartosPST", notes = "crear un cuarto de acuerdo a al codigo de un hospedaje", response = CuartosPSTRs.class, tags={ "Hospedajes", })
	public Response cuartosPST(@PathParam(value = "codigo") Long codigo,@RequestBody CuartosPSTRq cuartosPSTRq) {
		CuartosPSTRs cuartosPSTRs = new CuartosPSTRs();
		try {
			Cuarto cuarto = cuartoService.crearCuarto(cuartosPSTRq.getCuarto(),codigo);
    		cuartosPSTRs.setCuarto(cuarto);
        	return Response.status(Response.Status.OK).entity(cuartosPSTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PATCH
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}/cuartos/{idCuarto}")
	@ApiOperation(value = "Actualizar o reservar un cuarto de acuerdo a un hospedaje", nickname = "cuartosPTC", notes = "Actualizar o reservar un cuarto de acuerdo a un codigo de hospedaje", response = CuartosPTCRs.class, tags={ "Hospedajes", })
	public Response cuartosPTC(@PathParam(value = "codigo") Long codigo,@PathParam(value = "idCuarto") Long cuartoId,@RequestBody CuartosPTCRq cuartosPTCRq,@QueryParam(value= "reservar") boolean reservar) {
		CuartosPTCRs cuartosPTCRs = new CuartosPTCRs();
		try {
			Cuarto cuarto = cuartoService.actualizarCuarto(cuartosPTCRq.getCuarto(), cuartoId, reservar);
    		cuartosPTCRs.setCuarto(cuarto);
        	return Response.status(Response.Status.OK).entity(cuartosPTCRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (HospedajeException e) {
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
