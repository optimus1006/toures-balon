package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Cuarto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de creacion de un cuarto
 */
@ApiModel(description = "Respuesta de creacion de un cuarto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-17T17:16:40.601-05:00[America/Bogota]")

public class CuartosPTCRs   {
  @JsonProperty("cuarto")
  private Cuarto cuarto = null;

  public CuartosPTCRs cuarto(Cuarto cuarto) {
    this.cuarto = cuarto;
    return this;
  }

  /**
   * Get cuarto
   * @return cuarto
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Cuarto getCuarto() {
    return cuarto;
  }

  public void setCuarto(Cuarto cuarto) {
    this.cuarto = cuarto;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CuartosPTCRs cuartosPTCRs = (CuartosPTCRs) o;
    return Objects.equals(this.cuarto, cuartosPTCRs.cuarto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cuarto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CuartosPTCRs {\n");
    
    sb.append("    cuarto: ").append(toIndentedString(cuarto)).append("\n");
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

