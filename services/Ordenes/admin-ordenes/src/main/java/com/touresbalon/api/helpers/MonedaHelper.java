package com.touresbalon.api.helpers;

import com.touresbalon.api.domain.Moneda;
import com.touresbalon.api.entities.MonedaEntity;

public class MonedaHelper {

    public static MonedaEntity monedaToMonedaEntity(Moneda moneda, MonedaEntity monedaEntity) {
        if(monedaEntity == null) {
            monedaEntity = new MonedaEntity();
        }
        monedaEntity.setAmount(moneda.getAmount());
        monedaEntity.setCurrency(moneda.getCurrency());

        return monedaEntity;
    }

    public static Moneda monedaEntityToMoneda(MonedaEntity monedaEntity, Moneda moneda) {
        if(moneda == null) {
            moneda = new Moneda();
        }
        moneda.setAmount(monedaEntity.getAmount());
        moneda.setCurrency(monedaEntity.getCurrency());

        return moneda;
    }
}
