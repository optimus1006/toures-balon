package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Transporte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TransportesGETAllRs
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class TransportesGETAllRs   {
  @JsonProperty("transportes")
  @Valid
  private List<Transporte> transportes = new ArrayList<>();

  public TransportesGETAllRs transportes(List<Transporte> transportes) {
    this.transportes = transportes;
    return this;
  }

  public TransportesGETAllRs addTransportesItem(Transporte transportesItem) {
    this.transportes.add(transportesItem);
    return this;
  }

  /**
   * Get transportes
   * @return transportes
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Transporte> getTransportes() {
    return transportes;
  }

  public void setTransportes(List<Transporte> transportes) {
    this.transportes = transportes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransportesGETAllRs transportesGETAllRs = (TransportesGETAllRs) o;
    return Objects.equals(this.transportes, transportesGETAllRs.transportes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transportes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransportesGETAllRs {\n");
    
    sb.append("    transportes: ").append(toIndentedString(transportes)).append("\n");
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

