package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Cuarto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * tipo de acomodacion para un hospedaje.
 */
@ApiModel(description = "tipo de acomodacion para un hospedaje.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-24T16:31:00.109-05:00[America/Bogota]")

public class Acomodacion   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("cantidadCuartos")
  private Integer cantidadCuartos;

  @JsonProperty("precio")
  private Double precio;

  @JsonProperty("cuartos")
  @Valid
  private List<Cuarto> cuartos = null;

  @JsonProperty("codigoExterno")
  private String codigoExterno;

  public Acomodacion id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Acomodacion nombre(String nombre) {
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

  public Acomodacion descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  @ApiModelProperty(value = "")


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Acomodacion cantidadCuartos(Integer cantidadCuartos) {
    this.cantidadCuartos = cantidadCuartos;
    return this;
  }

  /**
   * Get cantidadCuartos
   * @return cantidadCuartos
  */
  @ApiModelProperty(value = "")


  public Integer getCantidadCuartos() {
    return cantidadCuartos;
  }

  public void setCantidadCuartos(Integer cantidadCuartos) {
    this.cantidadCuartos = cantidadCuartos;
  }

  public Acomodacion precio(Double precio) {
    this.precio = precio;
    return this;
  }

  /**
   * Get precio
   * @return precio
  */
  @ApiModelProperty(value = "")


  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public Acomodacion cuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
    return this;
  }

  public Acomodacion addCuartosItem(Cuarto cuartosItem) {
    if (this.cuartos == null) {
      this.cuartos = new ArrayList<>();
    }
    this.cuartos.add(cuartosItem);
    return this;
  }

  /**
   * indica la cantidad de cuartos reservados en una acomodacion
   * @return cuartos
  */
  @ApiModelProperty(value = "indica la cantidad de cuartos reservados en una acomodacion")

  @Valid

  public List<Cuarto> getCuartos() {
    return cuartos;
  }

  public void setCuartos(List<Cuarto> cuartos) {
    this.cuartos = cuartos;
  }

  public Acomodacion codigoExterno(String codigoExterno) {
    this.codigoExterno = codigoExterno;
    return this;
  }

  /**
   * codigo designado por el convenio para la homologacion
   * @return codigoExterno
  */
  @ApiModelProperty(value = "codigo designado por el convenio para la homologacion")


  public String getCodigoExterno() {
    return codigoExterno;
  }

  public void setCodigoExterno(String codigoExterno) {
    this.codigoExterno = codigoExterno;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Acomodacion acomodacion = (Acomodacion) o;
    return Objects.equals(this.id, acomodacion.id) &&
        Objects.equals(this.nombre, acomodacion.nombre) &&
        Objects.equals(this.descripcion, acomodacion.descripcion) &&
        Objects.equals(this.cantidadCuartos, acomodacion.cantidadCuartos) &&
        Objects.equals(this.precio, acomodacion.precio) &&
        Objects.equals(this.cuartos, acomodacion.cuartos) &&
        Objects.equals(this.codigoExterno, acomodacion.codigoExterno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, descripcion, cantidadCuartos, precio, cuartos, codigoExterno);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Acomodacion {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    cantidadCuartos: ").append(toIndentedString(cantidadCuartos)).append("\n");
    sb.append("    precio: ").append(toIndentedString(precio)).append("\n");
    sb.append("    cuartos: ").append(toIndentedString(cuartos)).append("\n");
    sb.append("    codigoExterno: ").append(toIndentedString(codigoExterno)).append("\n");
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

