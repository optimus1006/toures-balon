package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import com.touresbalon.ordenes.entities.TarjetaEntity;
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
        List<OrdenItemEntity> itemList = new ArrayList<>();
        if(orden.getEvento() != null){
            for (Evento evento:orden.getEvento()
                 ) {
                itemList.add(OrdenItemHelper.ordenItemToOrdenItemEntity(new OrdenItem(Long.valueOf(evento.getId()), EnumTipoProducto.EVENTO), null));
            }
        }
        if(orden.getTransporte() != null){
            for (Transporte transporte:orden.getTransporte()
            ) {
                itemList.add(OrdenItemHelper.ordenItemToOrdenItemEntity(new OrdenItem(transporte.getId(), EnumTipoProducto.TRANSPORTE), null));
            }
        }
        if(orden.getHospedaje() != null){
            for (Hospedaje hospedaje:orden.getHospedaje()
            ) {
                itemList.add(OrdenItemHelper.ordenItemToOrdenItemEntity(new OrdenItem(hospedaje.getCodigo(), EnumTipoProducto.HOSPEDAJE), null));
            }
        }
        ordenEntity.setItems(itemList);

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
}
