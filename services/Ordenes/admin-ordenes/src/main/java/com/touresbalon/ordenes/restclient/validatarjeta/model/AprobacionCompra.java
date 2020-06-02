package com.touresbalon.ordenes.restclient.validatarjeta.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

