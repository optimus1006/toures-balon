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
 * Estado de cliente , puede ser ACTIVO o INACTIVO
 */
@ApiModel(description = "Estado de cliente , puede ser ACTIVO o INACTIVO")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Estado   {
  @JsonProperty("codigo")
  private BigDecimal codigo;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("pais")
  private Pais pais;

  public Estado codigo(BigDecimal codigo) {
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

  public Estado pais(Pais pais) {
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
    Estado estado = (Estado) o;
    return Objects.equals(this.codigo, estado.codigo) &&
        Objects.equals(this.nombre, estado.nombre) &&
        Objects.equals(this.pais, estado.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Estado {\n");
    
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

