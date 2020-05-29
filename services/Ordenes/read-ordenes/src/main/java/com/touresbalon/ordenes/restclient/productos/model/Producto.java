package com.touresbalon.ordenes.restclient.productos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.touresbalon.ordenes.api.model.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * paquete que contiene todos los componentes necesarios para un plan
 */
@ApiModel(description = "paquete que contiene todos los componentes necesarios para un plan")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-23T10:55:03.920-05:00[America/Bogota]")

public class Producto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("fechaCreacion")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaCreacion;

  @JsonProperty("detalleProducto")
  @Valid
  private List<DetalleProducto> detalleProducto = new ArrayList<>();

  @JsonProperty("cliente")
  private Cliente cliente;

  @JsonProperty("precio")
  private Double precio;

  public Producto id(Long id) {
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

  public Producto descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Producto fechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Get fechaCreacion
   * @return fechaCreacion
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Producto detalleProducto(List<DetalleProducto> detalleProducto) {
    this.detalleProducto = detalleProducto;
    return this;
  }

  public Producto addDetalleProductoItem(DetalleProducto detalleProductoItem) {
    this.detalleProducto.add(detalleProductoItem);
    return this;
  }

  /**
   * Get detalleProducto
   * @return detalleProducto
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<DetalleProducto> getDetalleProducto() {
    return detalleProducto;
  }

  public void setDetalleProducto(List<DetalleProducto> detalleProducto) {
    this.detalleProducto = detalleProducto;
  }

  public Producto cliente(Cliente cliente) {
    this.cliente = cliente;
    return this;
  }

  /**
   * Get cliente
   * @return cliente
  */
  @ApiModelProperty(value = "")

  @Valid

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Producto precio(Double precio) {
    this.precio = precio;
    return this;
  }

  /**
   * Get precio
   * @return precio
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
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
        Objects.equals(this.detalleProducto, producto.detalleProducto) &&
        Objects.equals(this.cliente, producto.cliente) &&
        Objects.equals(this.precio, producto.precio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descripcion, fechaCreacion, detalleProducto, cliente, precio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Producto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    detalleProducto: ").append(toIndentedString(detalleProducto)).append("\n");
    sb.append("    cliente: ").append(toIndentedString(cliente)).append("\n");
    sb.append("    precio: ").append(toIndentedString(precio)).append("\n");
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

