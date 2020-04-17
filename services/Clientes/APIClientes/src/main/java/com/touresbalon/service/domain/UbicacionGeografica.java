package com.touresbalon.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ubicación geográfica
 */
@ApiModel(description = "Ubicación geográfica")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

public class UbicacionGeografica   {
  @JsonProperty("latitud")
  private Float latitud;

  @JsonProperty("longitud")
  private Float longitud;

  public UbicacionGeografica latitud(Float latitud) {
    this.latitud = latitud;
    return this;
  }

  /**
   * Get latitud
   * @return latitud
  */
  @ApiModelProperty(value = "")


  public Float getLatitud() {
    return latitud;
  }

  public void setLatitud(Float latitud) {
    this.latitud = latitud;
  }

  public UbicacionGeografica longitud(Float longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * Get longitud
   * @return longitud
  */
  @ApiModelProperty(value = "")


  public Float getLongitud() {
    return longitud;
  }

  public void setLongitud(Float longitud) {
    this.longitud = longitud;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UbicacionGeografica ubicacionGeografica = (UbicacionGeografica) o;
    return Objects.equals(this.latitud, ubicacionGeografica.latitud) &&
        Objects.equals(this.longitud, ubicacionGeografica.longitud);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitud, longitud);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UbicacionGeografica {\n");
    
    sb.append("    latitud: ").append(toIndentedString(latitud)).append("\n");
    sb.append("    longitud: ").append(toIndentedString(longitud)).append("\n");
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

