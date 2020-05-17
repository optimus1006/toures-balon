package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Transporte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de  actualizacion de un transporte.
 */
@ApiModel(description = "Respuesta de  actualizacion de un transporte.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class TransportesPCTRs   {
  @JsonProperty("transporte")
  private Transporte transporte = null;

  public TransportesPCTRs transporte(Transporte transporte) {
    this.transporte = transporte;
    return this;
  }

  /**
   * Get transporte
   * @return transporte
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Transporte getTransporte() {
    return transporte;
  }

  public void setTransporte(Transporte transporte) {
    this.transporte = transporte;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransportesPCTRs transportesPCTRs = (TransportesPCTRs) o;
    return Objects.equals(this.transporte, transportesPCTRs.transporte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transporte);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransportesPCTRs {\n");
    
    sb.append("    transporte: ").append(toIndentedString(transporte)).append("\n");
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

