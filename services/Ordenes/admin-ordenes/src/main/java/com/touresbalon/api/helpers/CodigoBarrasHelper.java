package com.touresbalon.api.helpers;

import com.touresbalon.api.domain.CodigoBarras;
import com.touresbalon.api.entities.CodigoBarrasEntity;

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
