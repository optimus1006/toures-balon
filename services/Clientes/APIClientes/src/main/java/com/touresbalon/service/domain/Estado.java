package com.touresbalon.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.service.domain.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Estado de cliente , puede ser ACTIVO o INACTIVO
 */
@ApiModel(description = "Estado de cliente , puede ser ACTIVO o INACTIVO")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

public class Estado   {
  @JsonProperty("codigo")
  private int codigo;

  @JsonProperty("nombre")
  private String nombre;

  public Estado codigo(int codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Código del estado.
   * minimum: 1
   * @return codigo
  */
  @ApiModelProperty(value = "Código del estado.")

  @Valid
@DecimalMin("1")
  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Estado nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Nombre del estado.
   * @return nombre
  */
  @ApiModelProperty(value = "Nombre del estado.")

@Size(min=1,max=64) 
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Estado estado = (Estado) o;
    return Objects.equals(this.codigo, estado.codigo) &&
        Objects.equals(this.nombre, estado.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
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

