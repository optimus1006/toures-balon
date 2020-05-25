package com.tourebalon.mock.model;

/**
 * Tipo Identificacion
 */
public class TipoIdentificacionValidacion {
  public TipoIdentificacionValidacion() {
  }

  public TipoIdentificacionValidacion(String codigo, String nombre) {
    this.codigo = codigo;
    this.nombre = nombre;
  }

  private String codigo;

  private String nombre;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}

