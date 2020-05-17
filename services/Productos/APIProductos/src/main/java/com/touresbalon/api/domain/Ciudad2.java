package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Pais2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ciudad
 */
@ApiModel(description = "Ciudad")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Ciudad2   {
  @JsonProperty("codigo")
  private BigDecimal codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("pais")
  private Pais2 pais;

  public Ciudad2 codigo(BigDecimal codigo) {
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
  public BigDecimal getCodigo() {
    return codigo;
  }

  public void setCodigo(BigDecimal codigo) {
    this.codigo = codigo;
  }

  public Ciudad2 nombre(String nombre) {
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

  public Ciudad2 pais(Pais2 pais) {
    this.pais = pais;
    return this;
  }

  /**
   * Get pais
   * @return pais
  */
  @ApiModelProperty(value = "")

  @Valid

  public Pais2 getPais() {
    return pais;
  }

  public void setPais(Pais2 pais) {
    this.pais = pais;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ciudad2 ciudad2 = (Ciudad2) o;
    return Objects.equals(this.codigo, ciudad2.codigo) &&
        Objects.equals(this.nombre, ciudad2.nombre) &&
        Objects.equals(this.pais, ciudad2.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ciudad2 {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

