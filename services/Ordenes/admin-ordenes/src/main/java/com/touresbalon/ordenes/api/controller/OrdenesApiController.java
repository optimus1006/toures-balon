package com.touresbalon.ordenes.api.controller;

import com.touresbalon.ordenes.api.model.Error;
import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.exceptions.OrdenException;
import com.touresbalon.ordenes.exceptions.OrdenNotFoundException;
import com.touresbalon.ordenes.service.OrdenService;
import io.swagger.annotations.ApiParam;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
public class OrdenesApiController implements OrdenesApi {

    private static final Logger log = LoggerFactory.getLogger(OrdenesApiController.class);

    @Inject
    OrdenService ordenService;

    @Context
    private HttpServerRequest request;

    public Response ordenesDEL(@ApiParam(value = "Código de la orden de pago.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Orden orden = new Orden();
                orden.setEstado(Orden.EstadoEnum.CERRADA);
                ordenService.actualizarOrden(orden, codigoOrden);
                return Response.status(Response.Status.OK).build();
            } catch (OrdenException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
            }
            catch (Exception e) {
                log.error("ordenesDEL ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response ordenesPCT(@ApiParam(value = "Código de la orden a cambiar de estado.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden,
                               @NotNull @ApiParam(value = "Orden con los camppos a actualizar",required=true)  @Valid @RequestBody OrdenesPSTRq orden)
    {
        OrdenesGetByIdRs ordenesGetByIdRs = new OrdenesGetByIdRs();
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                ordenService.actualizarOrden(orden.getOrden(), codigoOrden);
                ordenesGetByIdRs.setOrden(orden.getOrden());
                return Response.status(Response.Status.OK).entity(ordenesGetByIdRs).type(MediaType.APPLICATION_JSON).build();
            } catch (OrdenNotFoundException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.NOT_FOUND).entity(e).type(MediaType.APPLICATION_JSON).build();
            } catch (OrdenException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
            }
            catch (Exception e) {
                log.error("ordenesPCT ", e);
              return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response ordenesPST(@ApiParam(value = "Orden que va a ser creada"  )
                               @Valid @RequestBody OrdenesPSTRq ordenesPSTRq )
    {
        OrdenesPSTRs ordenesPSTRs = new OrdenesPSTRs();
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                ordenesPSTRs.setOrden(ordenService.crearOrden(ordenesPSTRq.getOrden()));
                return Response.status(Response.Status.OK).entity(ordenesPSTRs).type(MediaType.APPLICATION_JSON).build();
            } catch (OrdenNotFoundException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.NOT_FOUND).entity(e).type(MediaType.APPLICATION_JSON).build();
            }  catch (OrdenException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
            }
            catch (Exception e) {
                log.error("ordenesPST ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override
    public Response realizarCompra(@ApiParam(value = "Orden que va a ser creada por compra"  )
                                     @Valid @RequestBody CompraPSTRq compra) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                CompraPSTRs compraPSTRs = new CompraPSTRs();
                compraPSTRs.setOrden(ordenService.realizarCompra(compra.getOrden()));
                return Response.status(Response.Status.OK).entity(compraPSTRs).type(MediaType.APPLICATION_JSON).build();
            } catch (OrdenNotFoundException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.NOT_FOUND).entity(e).type(MediaType.APPLICATION_JSON).build();
            }  catch (OrdenException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
            }
            catch (Exception e) {
                log.error("realizarCompra ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    public Response facturaPST(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam("codigoOrden") Long codigoOrden
            , @ApiParam(value = ""  )  @Valid @RequestBody FacturaPSTRq factura
    ) {
        log.info("facturaPST " + factura);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                FacturaPSTRs facturaPSTRs = new FacturaPSTRs();
                facturaPSTRs.setFactura(new Factura());
                return Response.status(Response.Status.OK).entity(facturaPSTRs).type(MediaType.APPLICATION_JSON).build();
            } catch (Exception e) {
                log.error("facturaPST ",e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    public Response ordenFacturaPCT(@ApiParam(value = "Código de la orden.",required=true) @PathParam("codigoOrden") Long codigoOrden
            , @ApiParam(value = "Código de la factura.",required=true) @PathParam("codigoFactura") String codigoFactura
            , @ApiParam(value = ""  )  @Valid @RequestBody FacturaPSTRq factura
    ) {
        log.info("FacturaPSTRq " + factura);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                FacturaPSTRs facturaPSTRs = new FacturaPSTRs();
                facturaPSTRs.setFactura(factura.getFactura());
                return Response.status(Response.Status.OK).entity(facturaPSTRs).type(MediaType.APPLICATION_JSON).build();
            } catch (Exception e) {
                log.error("ordenFacturaPCT ",e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

}
