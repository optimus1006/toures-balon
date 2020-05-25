package com.touresbalon.ordenes.restclient.validatarjeta.model;

/**
 * Cliente
 */
public class ClienteValidacion {
  private String identificacion;

  private String nombres;

  private String apellidos;

  private String celular;

  private TipoIdentificacionValidacion tipoIdentificacion;

  public ClienteValidacion() {
  }

  public ClienteValidacion(String identificacion, String nombres, String apellidos, String celular, TipoIdentificacionValidacion tipoIdentificacion) {
    this.identificacion = identificacion;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.celular = celular;
    this.tipoIdentificacion = tipoIdentificacion;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public TipoIdentificacionValidacion getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(TipoIdentificacionValidacion tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }
}

