package com.touresbalon.ordenes.helpers;

import com.touresbalon.ordenes.api.model.Factura;
import com.touresbalon.ordenes.entities.FacturaEntity;

public class FacturaHelper {

    public static FacturaEntity facturaToFacturaEntity(Factura factura, FacturaEntity facturaEntity) {
        if(facturaEntity == null) {
            facturaEntity = new FacturaEntity();
        }
        facturaEntity.setCodigo(factura.getCodigo());
        facturaEntity.setFechaCreacion(factura.getFechaCreacion());
        facturaEntity.setValorTotal(factura.getValorTotal());
        if(factura.getCodigoBarras() != null){
            facturaEntity.setCodigoBarras(CodigoBarrasHelper.codigoBarrasToCodigoBarrasEntity(factura.getCodigoBarras(), null));
        }

        return facturaEntity;
    }

    public static Factura facturaEntityToFactura(FacturaEntity facturaEntity, Factura factura) {
        if(factura == null) {
            factura = new Factura();
        }
        factura.setCodigo(facturaEntity.getCodigo());
        factura.setValorTotal(facturaEntity.getValorTotal());
        if(facturaEntity.getFechaCreacion() != null) {
            factura.setFechaCreacion(facturaEntity.getFechaCreacion());
        }
        if(facturaEntity.getCodigoBarras() != null){
            factura.setCodigoBarras(CodigoBarrasHelper.codigoBarrasEntityToCodigoBarras(facturaEntity.getCodigoBarras(), null));
        }

        return factura;
    }
}
