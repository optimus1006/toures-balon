package com.touresbalon.ordenes.restclient.arm.model;

import java.math.BigDecimal;

/**
 * Contiene la información de la aprobacion de compra
 */

public class IngresoCompra {
  public IngresoCompra() {
  }

  private String uidPago;

  private String numeroTarjeta;

  private String numeroIdentificacion;

  private String tipoIdentificacion;

  private BigDecimal montoTotal;

  public String getUidPago() {
    return uidPago;
  }

  public void setUidPago(String uidPago) {
    this.uidPago = uidPago;
  }

  public String getNumeroTarjeta() {
    return numeroTarjeta;
  }

  public void setNumeroTarjeta(String numeroTarjeta) {
    this.numeroTarjeta = numeroTarjeta;
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public void setNumeroIdentificacion(String numeroIdentificacion) {
    this.numeroIdentificacion = numeroIdentificacion;
  }

  public String getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(String tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }

  public BigDecimal getMontoTotal() {
    return montoTotal;
  }

  public void setMontoTotal(BigDecimal montoTotal) {
    this.montoTotal = montoTotal;
  }
}

