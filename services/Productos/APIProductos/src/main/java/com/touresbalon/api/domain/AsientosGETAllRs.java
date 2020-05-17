package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Asiento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de todos los asientos existentes de un transporte
 */
@ApiModel(description = "Respuesta de todos los asientos existentes de un transporte")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T16:57:41.160-05:00[America/Bogota]")

public class AsientosGETAllRs   {
  @JsonProperty("asientos")
  @Valid
  private List<Asiento> asientos = new ArrayList<>();

  public AsientosGETAllRs asientos(List<Asiento> asientos) {
    this.asientos = asientos;
    return this;
  }

  public AsientosGETAllRs addAsientosItem(Asiento asientosItem) {
    this.asientos.add(asientosItem);
    return this;
  }

  /**
   * Get asientos
   * @return asientos
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Asiento> getAsientos() {
    return asientos;
  }

  public void setAsientos(List<Asiento> asientos) {
    this.asientos = asientos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AsientosGETAllRs asientosGETAllRs = (AsientosGETAllRs) o;
    return Objects.equals(this.asientos, asientosGETAllRs.asientos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asientos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AsientosGETAllRs {\n");
    
    sb.append("    asientos: ").append(toIndentedString(asientos)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

