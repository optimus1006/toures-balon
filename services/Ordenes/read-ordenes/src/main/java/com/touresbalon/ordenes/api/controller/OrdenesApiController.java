package com.touresbalon.ordenes.api.controller;

import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.api.model.Error;
import com.touresbalon.ordenes.exceptions.OrdenException;
import com.touresbalon.ordenes.exceptions.OrdenNotFoundException;
import com.touresbalon.ordenes.kafka.JaegerConfig;
import com.touresbalon.ordenes.service.OrdenService;
import io.opentracing.Span;
import io.swagger.annotations.ApiParam;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
public class OrdenesApiController implements OrdenesApi {

    private static final Logger log = LoggerFactory.getLogger(OrdenesApiController.class);

    @Inject
    OrdenService ordenService;

    @Context
    private HttpServerRequest request;

    @Inject
    JaegerConfig tracer;

    public Response ordenesGETPorCodigo(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
) {
        Span span = tracer.getTracer().buildSpan("Consultar orden por codigo").start();
        span.setTag("url", "/ordenes/{codigoOrden}");
        span.setTag("http.method", "GET");
        span.setTag("http.status_code", 200);
        span.finish();
        OrdenesGetByIdRs ordenesGetByIdRs = new OrdenesGetByIdRs();
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                ordenesGetByIdRs.setOrden(ordenService.findOrdenByCodigoOrden(codigoOrden));
                return Response.status(Response.Status.OK).entity(ordenesGetByIdRs).type(MediaType.APPLICATION_JSON).build();
            } catch (OrdenException e) {
                Error error=new Error();
                error.setCode("0");
                error.setMessage(e.getMessage());
                return Response.status(Response.Status.CONFLICT).entity(error).type(MediaType.APPLICATION_JSON).build();
            }
            catch (Exception e) {
                log.error("ordenesGETPorCodigo ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response ordenesGETFacturaPorCodigoOrden(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
)   {
        Span span = tracer.getTracer().buildSpan("Consultar factura por codigo de orden").start();
        span.setTag("url", "/ordenes/{codigoOrden}/factura");
        span.setTag("http.method", "GET");
        span.setTag("http.status_code", 200);
        span.finish();
        OrdenesGetFacturaByCodigoRs ordenesGetFacturaByCodigoRs = new OrdenesGetFacturaByCodigoRs();
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Orden orden = ordenService.findOrdenByCodigoOrden(codigoOrden);
                ordenesGetFacturaByCodigoRs.setFactura(orden.getFactura());
                return Response.status(Response.Status.OK).entity(ordenesGetFacturaByCodigoRs).type(MediaType.APPLICATION_JSON).build();
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
                log.error("ordenesGETFacturaPorCodigoOrden ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response ordenesGetAll(@Min(0) @Max(200) @ApiParam(value = "El número de ordenes a retornar.", required = false, defaultValue = "0") @Valid @QueryParam(value = "limit") Integer limit
        ,@ApiParam(value = "Identificador de la orden") @Valid @QueryParam(value = "codigoOrden" ) Long codigoOrden
        ,@ApiParam(value = "Código del cliente sobre el cual se filtrarán las ordenes de pago.") @Valid @QueryParam(value = "codigoCliente" ) Integer codigoCliente
        ,@ApiParam(value = "Fecha mínima de la búsqueda de órdenes.") @Valid @QueryParam(value = "fechaDesde" ) Date fechaDesde
        ,@ApiParam(value = "Fecha máxima de la búsqueda de órdenes.") @Valid @QueryParam(value = "fechaHasta" ) Date fechaHasta
        ,@ApiParam(value = "Estado de la orden.", allowableValues = "COTIZACION, PAGADA, EN_RESERVA, CERRADA, RECHAZADA, CANCELADA") @Valid @QueryParam(value = "estado" ) String estado
        )
    {
        Span span = tracer.getTracer().buildSpan("Consultar ordenes").start();
        span.setTag("url", "/ordenes");
        span.setTag("http.method", "GET");
        span.setTag("http.status_code", 200);
        span.finish();
        OrdenesGetAllRs ordenesGetAllRs = new OrdenesGetAllRs();
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                ordenesGetAllRs.setOrden(ordenService.search(limit,codigoOrden,codigoCliente,fechaDesde,fechaHasta,estado));
                return Response.status(Response.Status.OK).entity(ordenesGetAllRs).type(MediaType.APPLICATION_JSON).build();
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
                log.error("ordenesGetAll ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).type(MediaType.APPLICATION_JSON).build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
