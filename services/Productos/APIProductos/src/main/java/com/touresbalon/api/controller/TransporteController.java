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

import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.AsientosGETAllRs;
import com.touresbalon.api.domain.AsientosPSTRq;
import com.touresbalon.api.domain.AsientosPSTRs;
import com.touresbalon.api.domain.AsientosPTCRq;
import com.touresbalon.api.domain.AsientosPTCRs;
import com.touresbalon.api.domain.Error;
import com.touresbalon.api.domain.Transporte;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.domain.TransportesGETAllRs;
import com.touresbalon.api.domain.TransportesGETByIdRs;
import com.touresbalon.api.domain.TransportesPCTRq;
import com.touresbalon.api.domain.TransportesPCTRs;
import com.touresbalon.api.domain.TransportesPSTRq;
import com.touresbalon.api.domain.TransportesPSTRs;
import com.touresbalon.api.service.AsientoService;
import com.touresbalon.api.service.TransporteService;

import io.swagger.annotations.ApiOperation;

@Path("/productos/transportes")
public class TransporteController {

	@Inject
	TransporteService transporteService;
	
	@Inject
	AsientoService asientoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Consultar todos los transportes existentes", nickname = "transportesGETAll", notes = "Realiza la consulta completa de los transportes existentes para toures balon ", response = TransportesGETAllRs.class, tags={ "Transportes", })
    public Response findAll() {
    	TransportesGETAllRs transportesGETAllRs = new TransportesGETAllRs();
    	
    	try {
    		List<Transporte> transportes=transporteService.listarTransportes();
    		transportesGETAllRs.setTransportes(transportes);
        	return Response.status(Response.Status.CREATED).entity(transportesGETAllRs).type(MediaType.APPLICATION_JSON).build();
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


//    @DELETE
//    @Path("{id}")
//    public void delete(@PathParam long id) {
//        fruitRepository.deleteById(id);
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Crear un transporte", nickname = "transportesPST", notes = "Crea un transporte de acuerdo a los parametros dados", response = TransportesPSTRs.class, tags={ "Transportes", })
    public Response create(@RequestBody TransportesPSTRq transporte) throws TransporteException{
    	TransportesPSTRs transportesPSTRs = new TransportesPSTRs();
    	try {
    		Transporte transporteResponse=transporteService.crearTransporte(transporte.getTransporte());
    		transportesPSTRs.setTransporte(transporteResponse);
        	return Response.status(Response.Status.OK).entity(transportesPSTRs).type(MediaType.APPLICATION_JSON).build();
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

    @PATCH
    @Path("/{idTransporte}")
    @ApiOperation(value = "Actualizar un transporte", nickname = "transportesPCT", notes = "Actualizar un transporte de acuerdo a los parametros dados", response = TransportesPCTRs.class, tags={ "Transportes", })
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeColor(@RequestBody TransportesPCTRq transporte, @PathParam(value = "idTransporte") Long id) {
    	TransportesPCTRs transportesPCTRs = new TransportesPCTRs();
    	try {
    		transporteService.actualizarTransporte(transporte.getTransporte(), id);
    		Transporte transporteResponse = new Transporte();
    		transporteResponse.setId(id);
    		transportesPCTRs.setTransporte(transporteResponse);
        	return Response.status(Response.Status.OK).entity(transportesPCTRs).type(MediaType.APPLICATION_JSON).build();
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

    @GET
    @Path("/{idTransporte}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Consultar todos los transportes existentes", nickname = "transportesGETById", notes = "Realiza la consulta de un transporete existente de acuerdo a su id para toures balon ", response = TransportesGETByIdRs.class, tags={ "Transportes", })
    public Response findById(@PathParam(value = "idTransporte") Long id) {
    	TransportesGETByIdRs transportesGETByIdRs = new TransportesGETByIdRs();
    	try {
    		Transporte transporte=transporteService.getTransportePorId(id);
    		transportesGETByIdRs .setTransporte(transporte);
        	return Response.status(Response.Status.OK).entity(transportesGETByIdRs).type(MediaType.APPLICATION_JSON).build();
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
    
    @POST
	@Path("/{idTransporte}/asientos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Crear un asiento de acuerdo a un transporte", nickname = "asientosPST", notes = "crear un asiento de acuerdo a un id de transporte", response = AsientosPSTRs.class, tags={ "Transportes", })
	public Response asientosPST(@RequestBody AsientosPSTRq asiento, @PathParam(value = "idTransporte") Long id) {
    	AsientosPSTRs asientosPSTRs = new AsientosPSTRs();
    	Asiento asientoResponse = new Asiento();
    	try {
    		Long asientoId=asientoService.crearAsiento(asiento.getAsiento(), id,null);
    		asientoResponse.setId(asientoId);
    		asientosPSTRs.setAsiento(asientoResponse);
        	return Response.status(Response.Status.CREATED).entity(asientosPSTRs).type(MediaType.APPLICATION_JSON).build();
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
    
    @PATCH
	@Path("/{idTransporte}/asientos/{idAsiento}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Actualizar o reservar un asiento de acuerdo a un transporte", nickname = "asientosPTC", notes = "Actualizar o reservar un asiento de acuerdo a un id de transporte", response = AsientosPTCRs.class, tags={ "Transportes", })
    public Response asientosPTC(@RequestBody AsientosPTCRq asiento, @PathParam(value = "idTransporte") Long id, @PathParam(value = "idAsiento") Long idAsiento) {
    	AsientosPTCRs asientosPTCRs = new AsientosPTCRs();
    	Asiento asientoRs = new Asiento();
    	try {
    		asientoRs= asientoService.actualizarAsiento(id, asiento.getAsiento(),idAsiento);
    		asientosPTCRs.setAsiento(asientoRs);
        	return Response.status(Response.Status.OK).entity(asientosPTCRs).type(MediaType.APPLICATION_JSON).build();
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
    
    @GET
	@Path("/{idTransporte}/asientos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Consultar todos los asientos existentes por transporte", nickname = "asientosGETAll", notes = "Consultar todos los asientos existentes por transporte ", response = AsientosGETAllRs.class, tags={ "Transportes", })
    public Response asientosGETAll(@PathParam(value = "idTransporte") Long id, @QueryParam(value= "idCliente") Long idCliente,@QueryParam(value= "idAsiento") Long idAsiento) {
    	AsientosGETAllRs asientosGETAllRs = new AsientosGETAllRs();
    	List<Asiento> listaAsientos = new ArrayList<>();
    	try {
    		if(idAsiento!=null) {
    			listaAsientos.add(asientoService.buscarAsientoPorid(idAsiento));
    		}
    		else {
    			listaAsientos=asientoService.buscarPorTransporte(id, idCliente,null);
    		}
    		
    		asientosGETAllRs.setAsientos(listaAsientos);
        	return Response.status(Response.Status.OK).entity(asientosGETAllRs).type(MediaType.APPLICATION_JSON).build();
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
    
}
