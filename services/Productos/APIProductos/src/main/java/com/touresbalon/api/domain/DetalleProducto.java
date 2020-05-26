package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Evento;
import com.touresbalon.api.domain.Hospedaje;
import com.touresbalon.api.domain.Transporte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * detalle de un producto que contiene todos los transportes, eventos u hospedajes comprados por un cliente o armados por un asesor
 */
@ApiModel(description = "detalle de un producto que contiene todos los transportes, eventos u hospedajes comprados por un cliente o armados por un asesor")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-25T20:13:38.885-05:00[America/Bogota]")

public class DetalleProducto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("transporte")
  private Transporte transporte;

  @JsonProperty("hospedaje")
  private Hospedaje hospedaje;

  @JsonProperty("evento")
  private Evento evento;

  @JsonProperty("asientosEvento")
  private Integer asientosEvento;

  @JsonProperty("asientosTransporte")
  private Integer asientosTransporte;

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

  public DetalleProducto evento(Evento evento) {
    this.evento = evento;
    return this;
  }

  /**
   * Get evento
   * @return evento
  */
  @ApiModelProperty(value = "")

  @Valid

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }

  public DetalleProducto asientosEvento(Integer asientosEvento) {
    this.asientosEvento = asientosEvento;
    return this;
  }

  /**
   * hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles
   * @return asientosEvento
  */
  @ApiModelProperty(value = "hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles")


  public Integer getAsientosEvento() {
    return asientosEvento;
  }

  public void setAsientosEvento(Integer asientosEvento) {
    this.asientosEvento = asientosEvento;
  }

  public DetalleProducto asientosTransporte(Integer asientosTransporte) {
    this.asientosTransporte = asientosTransporte;
    return this;
  }

  /**
   * hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles
   * @return asientosTransporte
  */
  @ApiModelProperty(value = "hace referencia si es compra de un cliente a los asientos comprados si es un producto nuevo a ofreser los asientos disponibles")


  public Integer getAsientosTransporte() {
    return asientosTransporte;
  }

  public void setAsientosTransporte(Integer asientosTransporte) {
    this.asientosTransporte = asientosTransporte;
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
  public boolean equals(java.lang.Object o) {
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
        Objects.equals(this.evento, detalleProducto.evento) &&
        Objects.equals(this.asientosEvento, detalleProducto.asientosEvento) &&
        Objects.equals(this.asientosTransporte, detalleProducto.asientosTransporte) &&
        Objects.equals(this.cuartosHospedaje, detalleProducto.cuartosHospedaje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, transporte, hospedaje, evento, asientosEvento, asientosTransporte, cuartosHospedaje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetalleProducto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    transporte: ").append(toIndentedString(transporte)).append("\n");
    sb.append("    hospedaje: ").append(toIndentedString(hospedaje)).append("\n");
    sb.append("    evento: ").append(toIndentedString(evento)).append("\n");
    sb.append("    asientosEvento: ").append(toIndentedString(asientosEvento)).append("\n");
    sb.append("    asientosTransporte: ").append(toIndentedString(asientosTransporte)).append("\n");
    sb.append("    cuartosHospedaje: ").append(toIndentedString(cuartosHospedaje)).append("\n");
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

