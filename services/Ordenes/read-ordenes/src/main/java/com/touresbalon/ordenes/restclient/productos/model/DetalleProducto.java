package com.touresbalon.ordenes.restclient.productos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.ordenes.api.model.Evento;
import com.touresbalon.ordenes.api.model.Hospedaje;
import com.touresbalon.ordenes.api.model.Transporte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * detalle de un producto que contiene todos los transportes, eventos u hospedajes comprados por un cliente o armados por un asesor
 */
@ApiModel(description = "detalle de un producto que contiene todos los transportes, eventos u hospedajes comprados por un cliente o armados por un asesor")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-23T10:55:03.920-05:00[America/Bogota]")

public class DetalleProducto {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("transporte")
  private Transporte transporte;

  @JsonProperty("hospedaje")
  private Hospedaje hospedaje;

  @JsonProperty("evento")
  private Evento eventos;

  @JsonProperty("asientosEvento")
  private Integer asietosEvento;

  @JsonProperty("asientosTransporte")
  private Integer asietosTransporte;

  @JsonProperty("cuartosHospedaje")
  private Integer cuartosHospedaje;

  public DetalleProducto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DetalleProducto transporte(Transporte transporte) {
    this.transporte = transporte;
    return this;
  }

  /**
   * Get transporte
   * @return transporte
  */
  @ApiModelProperty(value = "")

  @Valid

  public Transporte getTransporte() {
    return transporte;
  }

  public void setTransporte(Transporte transporte) {
    this.transporte = transporte;
  }

  public DetalleProducto hospedaje(Hospedaje hospedaje) {
    this.hospedaje = hospedaje;
    return this;
  }

  /**
   * Get hospedaje
   * @return hospedaje
  */
  @ApiModelProperty(value = "")

  @Valid

  public Hospedaje getHospedaje() {
    return hospedaje;
  }

  public void setHospedaje(Hospedaje hospedaje) {
    this.hospedaje = hospedaje;
  }

  public DetalleProducto eventos(Evento eventos) {
    this.eventos = eventos;
    return this;
  }

  /**
   * Get eventos
   * @return eventos
  */
  @ApiModelProperty(value = "")

  @Valid

  public Evento getEventos() {
    return eventos;
  }

  public void setEventos(Evento eventos) {
    this.eventos = eventos;
  }

  public DetalleProducto asietosEvento(Integer asietosEvento) {
    this.asietosEvento = asietosEvento;
    return this;
  }

  /**
   * hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles
   * @return asietosEvento
  */
  @ApiModelProperty(value = "hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles")


  public Integer getAsietosEvento() {
    return asietosEvento;
  }

  public void setAsietosEvento(Integer asietosEvento) {
    this.asietosEvento = asietosEvento;
  }

  public DetalleProducto asietosTransporte(Integer asietosTransporte) {
    this.asietosTransporte = asietosTransporte;
    return this;
  }

  /**
   * hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles
   * @return asietosTransporte
  */
  @ApiModelProperty(value = "hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles")


  public Integer getAsietosTransporte() {
    return asietosTransporte;
  }

  public void setAsietosTransporte(Integer asietosTransporte) {
    this.asietosTransporte = asietosTransporte;
  }

  public DetalleProducto cuartosHospedaje(Integer cuartosHospedaje) {
    this.cuartosHospedaje = cuartosHospedaje;
    return this;
  }

  /**
   * hace referencia si es compra de un cliente a los cuartos reservados si es un producto nuevo a ofreser los cuartos disponibles
   * @return cuartosHospedaje
  */
  @ApiModelProperty(value = "hace referencia si es compra de un cliente a los cuartos reservados si es un producto nuevo a ofreser los cuartos disponibles")


  public Integer getCuartosHospedaje() {
    return cuartosHospedaje;
  }

  public void setCuartosHospedaje(Integer cuartosHospedaje) {
    this.cuartosHospedaje = cuartosHospedaje;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetalleProducto detalleProducto = (DetalleProducto) o;
    return Objects.equals(this.id, detalleProducto.id) &&
        Objects.equals(this.transporte, detalleProducto.transporte) &&
        Objects.equals(this.hospedaje, detalleProducto.hospedaje) &&
        Objects.equals(this.eventos, detalleProducto.eventos) &&
        Objects.equals(this.asietosEvento, detalleProducto.asietosEvento) &&
        Objects.equals(this.asietosTransporte, detalleProducto.asietosTransporte) &&
        Objects.equals(this.cuartosHospedaje, detalleProducto.cuartosHospedaje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, transporte, hospedaje, eventos, asietosEvento, asietosTransporte, cuartosHospedaje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetalleProducto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    transporte: ").append(toIndentedString(transporte)).append("\n");
    sb.append("    hospedaje: ").append(toIndentedString(hospedaje)).append("\n");
    sb.append("    eventos: ").append(toIndentedString(eventos)).append("\n");
    sb.append("    asietosEvento: ").append(toIndentedString(asietosEvento)).append("\n");
    sb.append("    asietosTransporte: ").append(toIndentedString(asietosTransporte)).append("\n");
    sb.append("    cuartosHospedaje: ").append(toIndentedString(cuartosHospedaje)).append("\n");
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

