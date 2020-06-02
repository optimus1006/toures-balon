package com.tourebalon.mock.model;

/**
 * Contiene la información de la aprobacion de compra
 */

public class AprobacionCompra {
  public AprobacionCompra() {
  }

  private String uidPago;

  private String numeroTarjeta;

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
}

