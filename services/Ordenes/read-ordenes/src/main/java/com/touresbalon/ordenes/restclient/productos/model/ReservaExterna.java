package com.touresbalon.ordenes.restclient.productos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * lista de codigos retornados por los convenios externos luego de realizar la reserva
 */
@ApiModel(description = "lista de codigos retornados por los convenios externos luego de realizar la reserva")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-24T16:31:00.109-05:00[America/Bogota]")

public class ReservaExterna {
  @JsonProperty("idCodigoExterno")
  private String idCodigoExterno;

  @JsonProperty("idreserva")
  private String idreserva;

  public ReservaExterna idCodigoExterno(String idCodigoExterno) {
    this.idCodigoExterno = idCodigoExterno;
    return this;
  }

  /**
   * Get idCodigoExterno
   * @return idCodigoExterno
  */
  @ApiModelProperty(value = "")


  public String getIdCodigoExterno() {
    return idCodigoExterno;
  }

  public void setIdCodigoExterno(String idCodigoExterno) {
    this.idCodigoExterno = idCodigoExterno;
  }

  public ReservaExterna idreserva(String idreserva) {
    this.idreserva = idreserva;
    return this;
  }

  /**
   * Get idreserva
   * @return idreserva
  */
  @ApiModelProperty(value = "")


  public String getIdreserva() {
    return idreserva;
  }

  public void setIdreserva(String idreserva) {
    this.idreserva = idreserva;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservaExterna reservaExterna = (ReservaExterna) o;
    return Objects.equals(this.idCodigoExterno, reservaExterna.idCodigoExterno) &&
        Objects.equals(this.idreserva, reservaExterna.idreserva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCodigoExterno, idreserva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservaExterna {\n");
    
    sb.append("    idCodigoExterno: ").append(toIndentedString(idCodigoExterno)).append("\n");
    sb.append("    idreserva: ").append(toIndentedString(idreserva)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

