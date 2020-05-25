package com.tourebalon.mock.model;

import java.math.BigDecimal;

/**
 * Contiene la información de la validcion de tarjeta
 */

public class CompraValidacion {
  private BigDecimal monto;
  private ClienteValidacion cliente;
  private TarjetaValidacion tarjeta;

  public BigDecimal getMonto() {
    return monto;
  }

  public void setMonto(BigDecimal monto) {
    this.monto = monto;
  }

  public ClienteValidacion getCliente() {
    return cliente;
  }

  public void setCliente(ClienteValidacion cliente) {
    this.cliente = cliente;
  }

  public TarjetaValidacion getTarjeta() {
    return tarjeta;
  }

  public void setTarjeta(TarjetaValidacion tarjeta) {
    this.tarjeta = tarjeta;
  }
}

