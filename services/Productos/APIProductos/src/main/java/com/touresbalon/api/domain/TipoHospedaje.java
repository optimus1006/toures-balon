package com.touresbalon.api.domain;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

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
  public static TipoHospedaje fromValue(String value) {
    for (TipoHospedaje b : TipoHospedaje.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

