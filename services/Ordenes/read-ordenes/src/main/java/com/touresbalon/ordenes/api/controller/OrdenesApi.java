/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.touresbalon.ordenes.api.controller;

import io.swagger.annotations.*;
import com.touresbalon.ordenes.api.model.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
@Api(value = "ordenes", description = "the ordenes API")
@Path("/ordenes")
public interface OrdenesApi {

    @ApiOperation(value = "Obtiene una orden específica.", nickname = "ordenesGETPorCodigo", notes = "Obtiene el detalle de una orden.", response = OrdenesGetByIdRs.class, tags={ "Orden", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operación de consulta exitosa.", response = OrdenesGetByIdRs.class),
        @ApiResponse(code = 400, message = "Mensaje con formato incorrecto.", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "No tiene autorización de realizar esta operación.", response = ErrorResponse.class),
        @ApiResponse(code = 403, message = "Solicitud denegada.", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Operacion no encontrada.", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Operación no soportada.", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Error en el procesamiento de la solicitud.", response = ErrorResponse.class) })
    @GET
    @Path("{codigoOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    Response ordenesGETPorCodigo(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
);


    @ApiOperation(value = "Obtiene la factura de una orden.", nickname = "ordenesGETPorCodigoOrden", notes = "Obtiene el detalle de la factura de una orden, con la información de impresión.", response = OrdenesGetByIdRs.class, tags={ "Orden", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operación de consulta exitosa.", response = OrdenesGetByIdRs.class),
        @ApiResponse(code = 400, message = "Mensaje con formato incorrecto.", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "No tiene autorización de realizar esta operación.", response = ErrorResponse.class),
        @ApiResponse(code = 403, message = "Solicitud denegada.", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Operacion no encontrada.", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Operación no soportada.", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Error en el procesamiento de la solicitud.", response = ErrorResponse.class) })
    @GET
    @Path("{codigoOrden}/factura")
    @Produces(MediaType.APPLICATION_JSON)
    Response ordenesGETFacturaPorCodigoOrden(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
);


    @ApiOperation(value = "Consultar las ordenes existentes", nickname = "ordenesGetAll", notes = "Realiza la consulta de las órdenes de pago dentro del sistema de Toures Balon.", response = OrdenesGetAllRs.class, tags={ "Orden", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta de órdenes exitosa.", response = OrdenesGetAllRs.class),
        @ApiResponse(code = 400, message = "Parametro invalido"),
        @ApiResponse(code = 401, message = "No autorizado"),
        @ApiResponse(code = 500, message = "Error del sistema") })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenesGetAll(@Min(0) @Max(200) @ApiParam(value = "El número de ordenes a retornar.", required = false, defaultValue = "0") @Valid @QueryParam(value = "limit") Integer limit
            ,@ApiParam(value = "Identificador de la orden") @Valid @QueryParam(value = "codigoOrden" ) Long codigoOrden
            ,@ApiParam(value = "Código del cliente sobre el cual se filtrarán las ordenes de pago.") @Valid @QueryParam(value = "codigoCliente" ) Integer codigoCliente
            ,@ApiParam(value = "Fecha mínima de la búsqueda de órdenes.") @Valid @QueryParam(value = "fechaDesde" ) Date fechaDesde
            ,@ApiParam(value = "Fecha máxima de la búsqueda de órdenes.") @Valid @QueryParam(value = "fechaHasta" ) Date fechaHasta
            ,@ApiParam(value = "Estado de la orden.", allowableValues = "COTIZACION, PAGADA, EN_RESERVA, CERRADA, RECHAZADA, CANCELADA") @Valid @QueryParam(value = "estado" ) String estado
);

}
