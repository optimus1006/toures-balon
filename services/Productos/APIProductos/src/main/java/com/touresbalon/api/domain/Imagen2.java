package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.ImagenDimensiones;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.io.Resource;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contiene la información de la imagen que se mostrará del evento.
 */
@ApiModel(description = "Contiene la información de la imagen que se mostrará del evento.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Imagen2   {
  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("path")
  private String path;

  @JsonProperty("dimensiones")
  private ImagenDimensiones dimensiones;

  @JsonProperty("data")
  private Resource data;

  public Imagen2 nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  @ApiModelProperty(value = "")


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Imagen2 path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  */
  @ApiModelProperty(value = "")


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Imagen2 dimensiones(ImagenDimensiones dimensiones) {
    this.dimensiones = dimensiones;
    return this;
  }

  /**
   * Get dimensiones
   * @return dimensiones
  */
  @ApiModelProperty(value = "")

  @Valid

  public ImagenDimensiones getDimensiones() {
    return dimensiones;
  }

  public void setDimensiones(ImagenDimensiones dimensiones) {
    this.dimensiones = dimensiones;
  }

  public Imagen2 data(Resource data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @ApiModelProperty(value = "")

  @Valid

  public Resource getData() {
    return data;
  }

  public void setData(Resource data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Imagen2 imagen2 = (Imagen2) o;
    return Objects.equals(this.nombre, imagen2.nombre) &&
        Objects.equals(this.path, imagen2.path) &&
        Objects.equals(this.dimensiones, imagen2.dimensiones) &&
        Objects.equals(this.data, imagen2.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, path, dimensiones, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Imagen2 {\n");
    
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    dimensiones: ").append(toIndentedString(dimensiones)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

