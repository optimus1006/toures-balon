package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Tipo Identificacion
 */
@ApiModel(description = "Tipo Identificacion")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class TipoIdentificacion   {
  @JsonProperty("codigo")
  private String codigo;

  @JsonProperty("nombre")
  private String nombre;

  public com.touresbalon.ordenes.api.model.TipoIdentificacion codigo(String codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Código del Tipo Identificacion.
   * minimum: 1
   * @return codigo
  */
  @ApiModelProperty(value = "Código del Tipo Identificacion.")

  @Valid
@DecimalMin("1")
  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public com.touresbalon.ordenes.api.model.TipoIdentificacion nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Nombre del Tipo Identificacion.
   * @return nombre
  */
  @ApiModelProperty(value = "Nombre del Tipo Identificacion.")

@Size(min=1,max=64) 
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    com.touresbalon.ordenes.api.model.TipoIdentificacion tipoIdentificacion = (com.touresbalon.ordenes.api.model.TipoIdentificacion) o;
    return Objects.equals(this.codigo, tipoIdentificacion.codigo) &&
        Objects.equals(this.nombre, tipoIdentificacion.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoIdentificacion {\n");
    
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
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
