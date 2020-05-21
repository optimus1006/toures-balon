package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * tipos de vehiculos para transportarse de un lugar a otro
 */
@ApiModel(description = "tipos de vehiculos para transportarse de un lugar a otro")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Vehiculo   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("tipoVehiculo")
  private TipoTransporte tipoVehiculo;

  @JsonProperty("marca")
  private String marca;

  @JsonProperty("modelo")
  private Integer modelo;

  @JsonProperty("referencia")
  private String referencia;

  public Vehiculo id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Vehiculo tipoVehiculo(TipoTransporte tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
    return this;
  }

  /**
   * Get tipoVehiculo
   * @return tipoVehiculo
  */
  @ApiModelProperty(value = "")

  @Valid

  public TipoTransporte getTipoVehiculo() {
    return tipoVehiculo;
  }

  public void setTipoVehiculo(TipoTransporte tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  public Vehiculo marca(String marca) {
    this.marca = marca;
    return this;
  }

  /**
   * Get marca
   * @return marca
  */
  @ApiModelProperty(value = "")


  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public Vehiculo modelo(Integer modelo) {
    this.modelo = modelo;
    return this;
  }

  /**
   * Get modelo
   * @return modelo
  */
  @ApiModelProperty(value = "")


  public Integer getModelo() {
    return modelo;
  }

  public void setModelo(Integer modelo) {
    this.modelo = modelo;
  }

  public Vehiculo referencia(String referencia) {
    this.referencia = referencia;
    return this;
  }

  /**
   * Get referencia
   * @return referencia
  */
  @ApiModelProperty(value = "")


  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehiculo vehiculo = (Vehiculo) o;
    return Objects.equals(this.id, vehiculo.id) &&
        Objects.equals(this.tipoVehiculo, vehiculo.tipoVehiculo) &&
        Objects.equals(this.marca, vehiculo.marca) &&
        Objects.equals(this.modelo, vehiculo.modelo) &&
        Objects.equals(this.referencia, vehiculo.referencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tipoVehiculo, marca, modelo, referencia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehiculo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tipoVehiculo: ").append(toIndentedString(tipoVehiculo)).append("\n");
    sb.append("    marca: ").append(toIndentedString(marca)).append("\n");
    sb.append("    modelo: ").append(toIndentedString(modelo)).append("\n");
    sb.append("    referencia: ").append(toIndentedString(referencia)).append("\n");
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

