package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Respuesta a la creacion de un evento
 */
@ApiModel(description = "Respuesta a la creacion de un evento")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T05:49:11.682Z[GMT]")
public class OrdenesPSTRs   {
  @JsonProperty("orden")
  private com.touresbalon.ordenes.api.model.Orden orden = null;

  public com.touresbalon.ordenes.api.model.OrdenesPSTRs orden(com.touresbalon.ordenes.api.model.Orden orden) {
    this.orden = orden;
    return this;
  }

  /**
   * Get orden
   * @return orden
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public com.touresbalon.ordenes.api.model.Orden getOrden() {
    return orden;
  }

  public void setOrden(com.touresbalon.ordenes.api.model.Orden orden) {
    this.orden = orden;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    com.touresbalon.ordenes.api.model.OrdenesPSTRs ordenesPSTRs = (com.touresbalon.ordenes.api.model.OrdenesPSTRs) o;
    return Objects.equals(this.orden, ordenesPSTRs.orden);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orden);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdenesPSTRs {\n");

    sb.append("    orden: ").append(toIndentedString(orden)).append("\n");
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
