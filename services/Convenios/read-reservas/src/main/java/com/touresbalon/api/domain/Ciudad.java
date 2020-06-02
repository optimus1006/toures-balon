package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Ciudad
 */
@ApiModel(description = "Ciudad")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Ciudad   {
  @JsonProperty("codigo")
  private int codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("pais")
  private Pais pais;

  public Ciudad codigo(int codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Código de la ciudad.
   * minimum: 1
   * @return codigo
  */
  @ApiModelProperty(value = "Código de la ciudad.")

  @Valid
@DecimalMin("1")
  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Ciudad nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Nombre de la ciudad.
   * @return nombre
  */
  @ApiModelProperty(value = "Nombre de la ciudad.")

@Size(min=1,max=64) 
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Ciudad pais(Pais pais) {
    this.pais = pais;
    return this;
  }

  /**
   * Get pais
   * @return pais
  */
  @ApiModelProperty(value = "")

  @Valid

  public Pais getPais() {
    return pais;
  }

  public void setPais(Pais pais) {
    this.pais = pais;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ciudad ciudad = (Ciudad) o;
    return Objects.equals(this.codigo, ciudad.codigo) &&
        Objects.equals(this.nombre, ciudad.nombre) &&
        Objects.equals(this.pais, ciudad.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ciudad {\n");
    
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
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

