package com.touresbalon.ordenes.api.model;

import com.touresbalon.ordenes.util.EnumTipoProducto;

public class OrdenItem {
    private Long codigo;

    private Integer cantidad;

    private EnumTipoProducto tipoProducto;

    public OrdenItem() {
    }

    public OrdenItem(Long codigo, Integer cantidad, EnumTipoProducto tipoProducto) {
        this.codigo = codigo;
        this.cantidad = cantidad;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
