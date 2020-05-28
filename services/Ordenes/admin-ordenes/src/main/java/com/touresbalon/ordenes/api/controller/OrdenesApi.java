package com.touresbalon.ordenes.api.controller;

import com.touresbalon.ordenes.api.model.*;
import io.swagger.annotations.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
@Api(value = "ordenes", description = "the ordenes API")
@Path("/ordenes")
public interface OrdenesApi {

    @ApiOperation(value = "Crea una factura.", nickname = "facturaPST", notes = "Crea la factura de una orden ya existente con los detalles del pago realizado.", response = FacturaPSTRs.class, tags={ "Factura", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Factura creada exitosamente", response = FacturaPSTRs.class),
            @ApiResponse(code = 400, message = "Parametro Inválido"),
            @ApiResponse(code = 409, message = "La factura ya estaba registrada para esta orden."),
            @ApiResponse(code = 500, message = "Error del sistema.") })
    @Path(value = "/ordenes/{codigoOrden}/factura")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Response facturaPST(@ApiParam(value = "Código identificador de la orden.",required=true) @PathParam("codigoOrden") Long codigoOrden
            , @ApiParam(value = ""  )  @Valid @RequestBody FacturaPSTRq factura
    );


    @ApiOperation(value = "Actualiza datos de la Factura de una orden.", nickname = "ordenFacturaPCT", notes = "Actualiza losa datos de una factura, en caso de requerir modificaciones de los valores calculados de la facturación.", response = OrdenesGetByIdRs.class, tags={ "Factura", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Se ha realizado la actualización de la factura de forma exitosa", response = OrdenesGetByIdRs.class),
            @ApiResponse(code = 400, message = "Ha ocurrido un error en el mensaje"),
            @ApiResponse(code = 500, message = "Ha ocurrido un error interno en el sistema") })
    @Path(value = "/ordenes/{codigoOrden}/{codigoFactura}")
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    Response ordenFacturaPCT(@ApiParam(value = "Código de la orden.",required=true) @PathParam("codigoOrden") Long codigoOrden
            , @ApiParam(value = "Código de la factura.",required=true) @PathParam("codigoFactura") String codigoFactura
            , @ApiParam(value = ""  )  @Valid @RequestBody FacturaPSTRq factura
    );

    @ApiOperation(value = "Deja el estado de la orden en un estado de terminación.", nickname = "ordenesDEL", notes = "Cambia el estado de la orden, ya sea a estado CERRADA, RECHAZADA o CANCELADA; de acuerdo al caso específico.", tags={ "Orden", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Orden actualizada exitosamente"),
        @ApiResponse(code = 400, message = "Error de parametros del request."),
        @ApiResponse(code = 409, message = "error en parametros de entrada.") })
    @DELETE
    @Path("{codigoOrden}/factura")
    @Produces(MediaType.APPLICATION_JSON)
    Response ordenesDEL(@ApiParam(value = "Código de la orden de pago.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden
);

    @ApiOperation(value = "Actualizar el estado de una orden.", nickname = "ordenesPCT", notes = "Actualizar el estado de una orden de pago, seleccionada por el codigo de la orden.", response = OrdenesGetByIdRs.class, tags={ "Transporte", })
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Se ha realizado la actualización exitosamente", response = OrdenesGetByIdRs.class),
        @ApiResponse(code = 400, message = "Ha ocurrido un error en el mensaje"),
        @ApiResponse(code = 500, message = "Ha ocurrido un error interno en el sistema") })
    @PATCH
    @Path("{codigoOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    Response ordenesPCT(@ApiParam(value = "Código de la orden a cambiar de estado.",required=true) @PathParam(value = "codigoOrden") Long codigoOrden,
                        @NotNull @ApiParam(value = "Orden con los camppos a actualizar",required=true)  @Valid @RequestBody OrdenesPSTRq orden
);


    @ApiOperation(value = "Recalcula una orden.", nickname = "ordenesPST", notes = "Realiza captura de los datos de la orden y recalcula el valor a pagar de la orden, asi como sus valores de descuenta y otros recargos.", response = OrdenesPSTRs.class, tags={ "Orden", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Orden creada exitosamente", response = OrdenesPSTRs.class),
        @ApiResponse(code = 400, message = "Parametro Invalido"),
        @ApiResponse(code = 409, message = "La orden ya estaba registrada."),
        @ApiResponse(code = 500, message = "Error del sistema.") })
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response ordenesPST(@ApiParam(value = ""  )  @Valid @RequestBody OrdenesPSTRq body);


    @ApiOperation(value = "Genera pago de orden.", nickname = "compraPST", notes = "Realiza captura de los datos de la orden y recalcula el valor a pagar de la orden, asi como sus valores de descuenta y otros recargos.", response = OrdenesPSTRs.class, tags={ "Orden", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pago realizado exitosamente", response = OrdenesPSTRs.class),
            @ApiResponse(code = 400, message = "Parametro Invalido"),
            @ApiResponse(code = 409, message = "La orden ya estaba registrada."),
            @ApiResponse(code = 500, message = "Error del sistema.") })
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pago")
    Response realizarCompra(@ApiParam(value = "Compra con la orden y sus items"  )  @Valid @RequestBody CompraPSTRq compra);

}
