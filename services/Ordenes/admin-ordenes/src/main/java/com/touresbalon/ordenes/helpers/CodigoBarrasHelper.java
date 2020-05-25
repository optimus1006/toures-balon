package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.CodigoBarras;
import com.touresbalon.ordenes.entities.CodigoBarrasEntity;

public class CodigoBarrasHelper {

    public static CodigoBarrasEntity codigoBarrasToCodigoBarrasEntity(CodigoBarras codigoBarras, CodigoBarrasEntity codigoBarrasEntity) {
        if(codigoBarrasEntity == null) {
            codigoBarrasEntity = new CodigoBarrasEntity();
        }
        codigoBarrasEntity.setTexto(codigoBarras.getTexto());
        codigoBarrasEntity.setTipo(codigoBarras.getTipo());

        return codigoBarrasEntity;
    }

    public static CodigoBarras codigoBarrasEntityToCodigoBarras(CodigoBarrasEntity codigoBarrasEntity, CodigoBarras codigoBarras) {
        if(codigoBarras == null) {
            codigoBarras = new CodigoBarras();
        }
        codigoBarras.setTexto(codigoBarrasEntity.getTexto());
        codigoBarras.setTipo(codigoBarrasEntity.getTipo());

        return codigoBarras;
    }
}
