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
  public boolean equals(java.lang.Object o) {
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

