package com.touresbalon.ordenes.api.model;

import com.touresbalon.ordenes.util.EnumTipoProducto;

public class OrdenItem {
    private Long codigo;

    private EnumTipoProducto tipoProducto;

    private Orden orden;

    public OrdenItem(Long codigo, EnumTipoProducto tipoProducto) {
        this.codigo = codigo;
        this.tipoProducto = tipoProducto;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public EnumTipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(EnumTipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
