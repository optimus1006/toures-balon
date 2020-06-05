package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contiene la información de la localidad dentro del evento, en caso de existir .
 */
@ApiModel(description = "Contiene la información de la localidad dentro del evento, en caso de existir .")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Localidad   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("precio")
  private Double precio;

  @JsonProperty("aforo")
  private Integer aforo;

  @JsonProperty("asientos")
  @Valid
  private List<Asiento> asientos = null;

  @JsonProperty("codigoExterno")
  private String codigoExterno;

  public Localidad id(Long id) {
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

  public Localidad nombre(String nombre) {
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

  public Localidad precio(Double precio) {
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

  public Localidad aforo(Integer aforo) {
    this.aforo = aforo;
    return this;
  }

  /**
   * Get aforo
   * @return aforo
   */
  @ApiModelProperty(value = "")


  public Integer getAforo() {
    return aforo;
  }

  public void setAforo(Integer aforo) {
    this.aforo = aforo;
  }

  public Localidad asientos(List<Asiento> asientos) {
    this.asientos = asientos;
    return this;
  }

  public Localidad addAsientosItem(Asiento asientosItem) {
    if (this.asientos == null) {
      this.asientos = new ArrayList<>();
    }
    this.asientos.add(asientosItem);
    return this;
  }

  /**
   * indica la cantidad de voletos comprados para el evento
   * @return asientos
   */
  @ApiModelProperty(value = "indica la cantidad de voletos comprados para el evento")

  @Valid

  public List<Asiento> getAsientos() {
    return asientos;
  }

  public void setAsientos(List<Asiento> asientos) {
    this.asientos = asientos;
  }

  public Localidad codigoExterno(String codigoExterno) {
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
    Localidad localidad = (Localidad) o;
    return Objects.equals(this.id, localidad.id) &&
            Objects.equals(this.nombre, localidad.nombre) &&
            Objects.equals(this.precio, localidad.precio) &&
            Objects.equals(this.aforo, localidad.aforo) &&
            Objects.equals(this.asientos, localidad.asientos) &&
            Objects.equals(this.codigoExterno, localidad.codigoExterno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, precio, aforo, asientos, codigoExterno);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Localidad {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    precio: ").append(toIndentedString(precio)).append("\n");
    sb.append("    aforo: ").append(toIndentedString(aforo)).append("\n");
    sb.append("    asientos: ").append(toIndentedString(asientos)).append("\n");
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

