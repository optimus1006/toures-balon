package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OrdenesGetAllRs
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T05:49:11.682Z[GMT]")
public class OrdenesGetAllRs   {
  @JsonProperty("orden")
  @Valid
  private List<Orden> orden = null;

  public OrdenesGetAllRs orden(List<Orden> orden) {
    this.orden = orden;
    return this;
  }

  public OrdenesGetAllRs addOrdenItem(Orden ordenItem) {
    if (this.orden == null) {
      this.orden = new ArrayList<Orden>();
    }
    this.orden.add(ordenItem);
    return this;
  }

  /**
   * Get orden
   * @return orden
  **/
  @ApiModelProperty(value = "")
  
    public List<Orden> getOrden() {
    return orden;
  }

  public void setOrden(List<Orden> orden) {
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
    OrdenesGetAllRs ordenesGetAllRs = (OrdenesGetAllRs) o;
    return Objects.equals(this.orden, ordenesGetAllRs.orden);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orden);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdenesGetAllRs {\n");

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
