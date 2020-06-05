package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.OrdenItem;
import com.touresbalon.ordenes.entities.OrdenItemEntity;


public class OrdenItemHelper {

    public static OrdenItemEntity ordenItemToOrdenItemEntity(OrdenItem ordenItem, OrdenItemEntity ordenItemEntity) {
        if(ordenItemEntity == null) {
            ordenItemEntity = new OrdenItemEntity();
        }
        ordenItemEntity.setCodigo(ordenItem.getCodigo());
        ordenItemEntity.setTipoProducto(ordenItem.getTipoProducto());
        ordenItemEntity.setCantidad(ordenItem.getCantidad());
        ordenItemEntity.setCodigoReserva(ordenItem.getCodigoReserva());

        return ordenItemEntity;
    }

    public static OrdenItem ordenItemEntityToOrdenItem(OrdenItemEntity ordenItemEntity, OrdenItem ordenItem) {
        if(ordenItem == null) {
            ordenItem = new OrdenItem();
        }
        ordenItem.setCodigo(ordenItemEntity.getCodigo());
        ordenItem.setTipoProducto(ordenItemEntity.getTipoProducto());
        ordenItem.setCantidad(ordenItemEntity.getCantidad());
        ordenItem.setCodigoReserva(ordenItemEntity.getCodigoReserva());

        return ordenItem;
    }
}
