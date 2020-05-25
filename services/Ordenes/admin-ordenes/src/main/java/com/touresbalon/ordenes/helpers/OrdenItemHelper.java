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
        if(ordenItem.getOrden() != null){
            ordenItemEntity.setOrden(OrdenHelper.ordenToOrdenEntity(ordenItem.getOrden(), null));
        }

        return ordenItemEntity;
    }
}
