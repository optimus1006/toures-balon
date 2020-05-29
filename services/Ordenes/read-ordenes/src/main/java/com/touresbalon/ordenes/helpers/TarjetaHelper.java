package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.Tarjeta;
import com.touresbalon.ordenes.entities.TarjetaEntity;

public class TarjetaHelper {

    public static TarjetaEntity tarjetaToTarjetaEntity(Tarjeta tarjeta, TarjetaEntity tarjetaEntity) {
        if(tarjetaEntity == null) {
            tarjetaEntity = new TarjetaEntity();
        }
        tarjetaEntity.setTipo(tarjeta.getTipo());
        tarjetaEntity.setNumero(tarjeta.getNumero());

        return tarjetaEntity;
    }

    public static Tarjeta tarjetaEntityToTarjeta(TarjetaEntity tarjetaEntity, Tarjeta tarjeta) {
        if(tarjeta == null) {
            tarjeta = new Tarjeta();
        }
        tarjeta.setTipo(tarjetaEntity.getTipo());
        tarjeta.setNumero(tarjetaEntity.getNumero());

        return tarjeta;
    }
}
