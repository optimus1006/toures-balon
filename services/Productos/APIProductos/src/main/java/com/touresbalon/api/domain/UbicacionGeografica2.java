package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ubicación geográfica de un sitio, definido por coordenadas polares (latitud y longitud).
 */
@ApiModel(description = "Ubicación geográfica de un sitio, definido por coordenadas polares (latitud y longitud).")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class UbicacionGeografica2   {
  @JsonProperty("latitud")
  private Float latitud;

  @JsonProperty("longitud")
  private Float longitud;

  public UbicacionGeografica2 latitud(Float latitud) {
    this.latitud = latitud;
    return this;
  }

  /**
   * Coordenada de latitud.
   * @return latitud
  */
  @ApiModelProperty(value = "Coordenada de latitud.")


  public Float getLatitud() {
    return latitud;
  }

  public void setLatitud(Float latitud) {
    this.latitud = latitud;
  }

  public UbicacionGeografica2 longitud(Float longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * Coordenada de longitud.
   * @return longitud
  */
  @ApiModelProperty(value = "Coordenada de longitud.")


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
    UbicacionGeografica2 ubicacionGeografica2 = (UbicacionGeografica2) o;
    return Objects.equals(this.latitud, ubicacionGeografica2.latitud) &&
        Objects.equals(this.longitud, ubicacionGeografica2.longitud);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitud, longitud);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UbicacionGeografica2 {\n");
    
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

