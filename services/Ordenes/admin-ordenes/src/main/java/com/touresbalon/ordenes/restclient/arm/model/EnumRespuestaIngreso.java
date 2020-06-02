package com.touresbalon.ordenes.restclient.arm.model;

public enum EnumRespuestaIngreso {
    
    RESPUESTA_EXITOSA("00"),
    RESPUESTA_FALLIDA("99"),
    ;

    private final String codigo;

    EnumRespuestaIngreso(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
