package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * ImagenDimensiones
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class ImagenDimensiones   {
  @JsonProperty("ancho")
  private Integer ancho;

  @JsonProperty("alto")
  private Integer alto;

  public ImagenDimensiones ancho(Integer ancho) {
    this.ancho = ancho;
    return this;
  }

  /**
   * Get ancho
   * minimum: 1
   * @return ancho
  */
  @ApiModelProperty(value = "")

@Min(1)
  public Integer getAncho() {
    return ancho;
  }

  public void setAncho(Integer ancho) {
    this.ancho = ancho;
  }

  public ImagenDimensiones alto(Integer alto) {
    this.alto = alto;
    return this;
  }

  /**
   * Get alto
   * minimum: 1
   * @return alto
  */
  @ApiModelProperty(value = "")

@Min(1)
  public Integer getAlto() {
    return alto;
  }

  public void setAlto(Integer alto) {
    this.alto = alto;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImagenDimensiones imagenDimensiones = (ImagenDimensiones) o;
    return Objects.equals(this.ancho, imagenDimensiones.ancho) &&
        Objects.equals(this.alto, imagenDimensiones.alto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ancho, alto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImagenDimensiones {\n");
    
    sb.append("    ancho: ").append(toIndentedString(ancho)).append("\n");
    sb.append("    alto: ").append(toIndentedString(alto)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

