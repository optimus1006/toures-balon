package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Tipos de hotel posibles.
 */
public enum TipoHospedaje {
  
  HOTEL("HOTEL"),
  
  MOTEL("MOTEL"),
  
  RESORT("RESORT"),
  
  VILLA("VILLA"),
  
  CABA_A("CABAÑA");

  private String value;

  TipoHospedaje(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static com.touresbalon.ordenes.api.model.TipoHospedaje fromValue(String value) {
    for (com.touresbalon.ordenes.api.model.TipoHospedaje b : com.touresbalon.ordenes.api.model.TipoHospedaje.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

