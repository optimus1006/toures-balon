package com.touresbalon.api.controller;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.touresbalon.api.domain.Error;
import com.touresbalon.api.domain.ProductoException;
import com.touresbalon.api.domain.ProductosPSTRq;
import com.touresbalon.api.domain.ProductosPSTRs;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.service.ProductoService;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/productos")
public class ProductoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Inject
	ProductoService productoService;
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Crear paquete de productos", nickname = "productosPST", notes = "Crear paquete de productos", response = ProductosPSTRs.class, tags={ "Productos", })
	public Response productosPST(@RequestBody ProductosPSTRq productosPSTRq) {
		ProductosPSTRs productosPSTRs = new ProductosPSTRs();
		try {
			productosPSTRs=productoService.crearProducto(productosPSTRq.getProducto());
        	return Response.status(Response.Status.OK).entity(productosPSTRs).type(MediaType.APPLICATION_JSON).build();
    	}catch (ProductoException e) {
    		Error error=new Error();
    		error.setCode("0");
    		error.setMessage(e.getMessage());
			return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
    	catch (Exception e) {
			LOGGER.error("Error en creacion de productos ", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
