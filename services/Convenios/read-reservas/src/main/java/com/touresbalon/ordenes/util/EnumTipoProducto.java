package com.touresbalon.ordenes.util;

public enum EnumTipoProducto {
    HOSPEDAJE("COTIZACION"),

    TRANSPORTE("PAGADA"),

    EVENTO("EVENTO");

    private String value;

    EnumTipoProducto(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static EnumTipoProducto fromValue(String text) {
        for (EnumTipoProducto b : EnumTipoProducto.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
