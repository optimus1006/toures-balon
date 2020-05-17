package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Pais;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Estado
 */
@ApiModel(description = "Estado")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Estado2   {
  @JsonProperty("codigo")
  private BigDecimal codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("pais")
  private Pais pais;

  public Estado2 codigo(BigDecimal codigo) {
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
  public BigDecimal getCodigo() {
    return codigo;
  }

  public void setCodigo(BigDecimal codigo) {
    this.codigo = codigo;
  }

  public Estado2 nombre(String nombre) {
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

  public Estado2 pais(Pais pais) {
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Estado2 estado2 = (Estado2) o;
    return Objects.equals(this.codigo, estado2.codigo) &&
        Objects.equals(this.nombre, estado2.nombre) &&
        Objects.equals(this.pais, estado2.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Estado2 {\n");
    
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

