package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * OrdenesGetByIdRs
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-16T23:29:26.784Z[GMT]")
public class OrdenesGetFacturaByCodigoRs {
  @JsonProperty("factura")
  private Factura factura = null;

  public com.touresbalon.ordenes.api.model.OrdenesGetFacturaByCodigoRs factura(Factura factura) {
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
    com.touresbalon.ordenes.api.model.OrdenesGetFacturaByCodigoRs facturaesGetByIdRs = (com.touresbalon.ordenes.api.model.OrdenesGetFacturaByCodigoRs) o;
    return Objects.equals(this.factura, facturaesGetByIdRs.factura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(factura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdenesPSTRs {\n");

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
