package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Respuesta a la creación de una factura.
 */
@ApiModel(description = "Respuesta a la creación de una factura.")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T05:49:11.682Z[GMT]")
public class FacturaPSTRs   {
  @JsonProperty("factura")
  private Factura factura = null;

  public com.touresbalon.ordenes.api.model.FacturaPSTRs factura(Factura factura) {
    this.factura = factura;
    return this;
  }

  /**
   * Get factura
   * @return factura
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Factura getFactura() {
    return factura;
  }

  public void setFactura(Factura factura) {
    this.factura = factura;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    com.touresbalon.ordenes.api.model.FacturaPSTRs facturaPSTRs = (com.touresbalon.ordenes.api.model.FacturaPSTRs) o;
    return Objects.equals(this.factura, facturaPSTRs.factura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(factura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacturaPSTRs {\n");

    sb.append("    factura: ").append(toIndentedString(factura)).append("\n");
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
