package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import com.touresbalon.ordenes.entities.TarjetaEntity;
import com.touresbalon.ordenes.kafka.OrdenMessage;
import com.touresbalon.ordenes.util.EnumTipoProducto;

import java.util.ArrayList;
import java.util.List;

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
        if(orden.getTarjeta() != null){
            List<TarjetaEntity> tarjetas = new ArrayList<>();
            tarjetas.add(TarjetaHelper.tarjetaToTarjetaEntity(orden.getTarjeta(), null));
            ordenEntity.setTarjetas(tarjetas);
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
        if(ordenEntity.getTarjetas() != null){
            for (TarjetaEntity tarjeta :
                    ordenEntity.getTarjetas()) {
                orden.setTarjeta(TarjetaHelper.tarjetaEntityToTarjeta(tarjeta, null));
            }
        }

        return orden;
    }

    public static OrdenMessage ordenToOrdenMessage(Orden orden, OrdenMessage ordenMessage) {
        if(ordenMessage == null) {
            ordenMessage = new OrdenMessage();
        }
        ordenMessage.setCodigo(orden.getCodigo());
        ordenMessage.setFechaCreacion(orden.getFechaCreacion());
        ordenMessage.setFechaModificacion(orden.getFechaModificacion());
        ordenMessage.setCodigoCliente(orden.getCodigoCliente());
        ordenMessage.setEstado(orden.getEstado());
        ordenMessage.setDescuento(orden.getDescuento());
        ordenMessage.setSubtotal(orden.getSubtotal());
        ordenMessage.setImpuestos(orden.getImpuestos());
        ordenMessage.setValorTotal(orden.getValorTotal());
        ordenMessage.setMoneda(orden.getMoneda());
        ordenMessage.setFactura(orden.getFactura());
        ordenMessage.setTarjeta(orden.getTarjeta());

        return ordenMessage;
    }
}
