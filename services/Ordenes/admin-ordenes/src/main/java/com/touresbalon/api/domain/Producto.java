package com.touresbalon.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * paquete que contiene todos los componentes necesarios para un plan
 */
@ApiModel(description = "paquete que contiene todos los componentes necesarios para un plan")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class Producto   {
  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("fechaCreacion")
  private OffsetDateTime fechaCreacion;

  @JsonProperty("transportes")
  @Valid
  private List<Transporte> transportes = null;

  @JsonProperty("eventos")
  @Valid
  private List<Evento> eventos = null;

  @JsonProperty("hospedajes")
  @Valid
  private List<Hospedaje> hospedajes = null;

  public Producto id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Producto descripcion(String descripcion) {
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

  public Producto fechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Get fechaCreacion
   * @return fechaCreacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Producto transportes(List<Transporte> transportes) {
    this.transportes = transportes;
    return this;
  }

  public Producto addTransportesItem(Transporte transportesItem) {
    if (this.transportes == null) {
      this.transportes = new ArrayList<>();
    }
    this.transportes.add(transportesItem);
    return this;
  }

  /**
   * Get transportes
   * @return transportes
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Transporte> getTransportes() {
    return transportes;
  }

  public void setTransportes(List<Transporte> transportes) {
    this.transportes = transportes;
  }

  public Producto eventos(List<Evento> eventos) {
    this.eventos = eventos;
    return this;
  }

  public Producto addEventosItem(Evento eventosItem) {
    if (this.eventos == null) {
      this.eventos = new ArrayList<>();
    }
    this.eventos.add(eventosItem);
    return this;
  }

  /**
   * Get eventos
   * @return eventos
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
  }

  public Producto hospedajes(List<Hospedaje> hospedajes) {
    this.hospedajes = hospedajes;
    return this;
  }

  public Producto addHospedajesItem(Hospedaje hospedajesItem) {
    if (this.hospedajes == null) {
      this.hospedajes = new ArrayList<>();
    }
    this.hospedajes.add(hospedajesItem);
    return this;
  }

  /**
   * Get hospedajes
   * @return hospedajes
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Hospedaje> getHospedajes() {
    return hospedajes;
  }

  public void setHospedajes(List<Hospedaje> hospedajes) {
    this.hospedajes = hospedajes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Producto producto = (Producto) o;
    return Objects.equals(this.id, producto.id) &&
        Objects.equals(this.descripcion, producto.descripcion) &&
        Objects.equals(this.fechaCreacion, producto.fechaCreacion) &&
        Objects.equals(this.transportes, producto.transportes) &&
        Objects.equals(this.eventos, producto.eventos) &&
        Objects.equals(this.hospedajes, producto.hospedajes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descripcion, fechaCreacion, transportes, eventos, hospedajes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Producto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    transportes: ").append(toIndentedString(transportes)).append("\n");
    sb.append("    eventos: ").append(toIndentedString(eventos)).append("\n");
    sb.append("    hospedajes: ").append(toIndentedString(hospedajes)).append("\n");
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

