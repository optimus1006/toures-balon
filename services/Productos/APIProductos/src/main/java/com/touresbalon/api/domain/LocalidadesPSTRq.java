package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Localidad;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Peticion de creacion de una localidad
 */
@ApiModel(description = "Peticion de creacion de una localidad")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-21T22:42:58.217-05:00[America/Bogota]")

public class LocalidadesPSTRq   {
  @JsonProperty("localidad")
  private Localidad localidad = null;

  public LocalidadesPSTRq localidad(Localidad localidad) {
    this.localidad = localidad;
    return this;
  }

  /**
   * Get localidad
   * @return localidad
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Localidad getLocalidad() {
    return localidad;
  }

  public void setLocalidad(Localidad localidad) {
    this.localidad = localidad;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalidadesPSTRq localidadesPSTRq = (LocalidadesPSTRq) o;
    return Objects.equals(this.localidad, localidadesPSTRq.localidad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localidad);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalidadesPSTRq {\n");
    
    sb.append("    localidad: ").append(toIndentedString(localidad)).append("\n");
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

