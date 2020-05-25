package com.touresbalon.ordenes.restclient.validatarjeta.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.touresbalon.ordenes.api.model.Tarjeta;

/**
 * Contiene la información de la tarjeta
 */

public class TarjetaValidacion {
  public TarjetaValidacion() {
  }

  public TarjetaValidacion(String numero, TipoEnum tipo) {
    this.numero = numero;
    this.tipo = tipo;
  }

  private String numero;
  /**
   * Gets or Sets tipo
   */
  public enum TipoEnum {
    VISA("VISA"),

    MASTER_CARD("MASTER_CARD");

    private String value;

    TipoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TipoEnum fromValue(String value) {
      for (TipoEnum b : TipoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }
  private TipoEnum tipo;

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public TipoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoEnum tipo) {
    this.tipo = tipo;
  }
}

