package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Asiento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de creacion de un asiento
 */
@ApiModel(description = "Respuesta de creacion de un asiento")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T16:57:41.160-05:00[America/Bogota]")

public class AsientosPTCRs   {
  @JsonProperty("asiento")
  private Asiento asiento = null;

  public AsientosPTCRs asiento(Asiento asiento) {
    this.asiento = asiento;
    return this;
  }

  /**
   * Get asiento
   * @return asiento
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Asiento getAsiento() {
    return asiento;
  }

  public void setAsiento(Asiento asiento) {
    this.asiento = asiento;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AsientosPTCRs asientosPTCRs = (AsientosPTCRs) o;
    return Objects.equals(this.asiento, asientosPTCRs.asiento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asiento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AsientosPTCRs {\n");
    
    sb.append("    asiento: ").append(toIndentedString(asiento)).append("\n");
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

