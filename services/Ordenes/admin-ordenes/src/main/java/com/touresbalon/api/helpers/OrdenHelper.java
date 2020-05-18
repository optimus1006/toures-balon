package com.touresbalon.api.helpers;

import com.touresbalon.api.domain.Orden;
import com.touresbalon.api.entities.OrdenEntity;

public class OrdenHelper {

    public static OrdenEntity ordenToOrdenEntity(Orden orden, OrdenEntity ordenEntity) {
        if(ordenEntity == null) {
            ordenEntity = new OrdenEntity();
        }
        ordenEntity.setCodigo(orden.getCodigo());
        ordenEntity.setFechaCreacion(orden.getFechaCreacion());
        ordenEntity.setFechaModificacion(orden.getFechaModificacion());
        ordenEntity.setCodigoCliente(orden.getCodigoCliente());
        ordenEntity.setEstado(orden.getEstado());
        ordenEntity.setDescuento(orden.getDescuento());
        ordenEntity.setSubtotal(orden.getSubtotal());
        ordenEntity.setImpuestos(orden.getImpuestos());
        ordenEntity.setValorTotal(orden.getValorTotal());
        if(orden.getMoneda() != null){
            ordenEntity.setMoneda(MonedaHelper.monedaToMonedaEntity(orden.getMoneda(), null));
        }
        if(orden.getFactura() != null){
            ordenEntity.setFactura(FacturaHelper.facturaToFacturaEntity(orden.getFactura(), null));
        }

        return ordenEntity;
    }

    public static Orden ordenEntityToOrden(OrdenEntity ordenEntity, Orden orden) {
        if(orden == null) {
            orden = new Orden();
        }
        orden.setCodigo(ordenEntity.getCodigo());
        if(ordenEntity.getFechaCreacion() != null) {
            orden.setFechaCreacion(ordenEntity.getFechaCreacion());
        }
        if(ordenEntity.getFechaModificacion() != null) {
            orden.setFechaModificacion(ordenEntity.getFechaModificacion());
        }
        orden.setCodigoCliente(ordenEntity.getCodigoCliente());
        orden.setEstado(ordenEntity.getEstado());
        orden.setDescuento(ordenEntity.getDescuento());
        orden.setSubtotal(ordenEntity.getSubtotal());
        orden.setImpuestos(ordenEntity.getImpuestos());
        orden.setValorTotal(ordenEntity.getValorTotal());
        if(ordenEntity.getMoneda() != null){
            orden.setMoneda(MonedaHelper.monedaEntityToMoneda(ordenEntity.getMoneda(), null));
        }
        if(ordenEntity.getFactura() != null){
            orden.setFactura(FacturaHelper.facturaEntityToFactura(ordenEntity.getFactura(), null));
        }

        return orden;
    }
}
