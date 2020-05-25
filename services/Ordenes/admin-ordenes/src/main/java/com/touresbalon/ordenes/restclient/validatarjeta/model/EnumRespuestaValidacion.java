package com.touresbalon.ordenes.restclient.validatarjeta.model;

public enum EnumRespuestaValidacion {
    
    RESPUESTA_EXITOSA("00"),
    RESPUESTA_FALLIDA("99"),
    ;

    private final String codigo;

    EnumRespuestaValidacion(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
