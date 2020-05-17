package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Hospedaje;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HospedajesGetAllRs
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class HospedajesGetAllRs   {
  @JsonProperty("hospedajes")
  @Valid
  private List<Hospedaje> hospedajes = new ArrayList<>();

  public HospedajesGetAllRs hospedajes(List<Hospedaje> hospedajes) {
    this.hospedajes = hospedajes;
    return this;
  }

  public HospedajesGetAllRs addHospedajesItem(Hospedaje hospedajesItem) {
    this.hospedajes.add(hospedajesItem);
    return this;
  }

  /**
   * Get hospedajes
   * @return hospedajes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Hospedaje> getHospedajes() {
    return hospedajes;
  }

  public void setHospedajes(List<Hospedaje> hospedajes) {
    this.hospedajes = hospedajes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HospedajesGetAllRs hospedajesGetAllRs = (HospedajesGetAllRs) o;
    return Objects.equals(this.hospedajes, hospedajesGetAllRs.hospedajes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hospedajes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HospedajesGetAllRs {\n");
    
    sb.append("    hospedajes: ").append(toIndentedString(hospedajes)).append("\n");
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

