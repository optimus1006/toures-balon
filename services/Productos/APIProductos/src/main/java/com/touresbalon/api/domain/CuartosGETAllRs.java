package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Cuarto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de todos los cuartos existentes de un hospedaje
 */
@ApiModel(description = "Respuesta de todos los cuartos existentes de un hospedaje")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-17T17:16:40.601-05:00[America/Bogota]")

public class CuartosGETAllRs   {
  @JsonProperty("cuartos")
  @Valid
  private List<Cuarto> cuartos = new ArrayList<>();

  public CuartosGETAllRs cuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
    return this;
  }

  public CuartosGETAllRs addCuartosItem(Cuarto cuartosItem) {
    this.cuartos.add(cuartosItem);
    return this;
  }

  /**
   * Get cuartos
   * @return cuartos
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Cuarto> getCuartos() {
    return cuartos;
  }

  public void setCuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CuartosGETAllRs cuartosGETAllRs = (CuartosGETAllRs) o;
    return Objects.equals(this.cuartos, cuartosGETAllRs.cuartos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cuartos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CuartosGETAllRs {\n");
    
    sb.append("    cuartos: ").append(toIndentedString(cuartos)).append("\n");
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

