package com.touresbalon.ordenes.restclient.productos.model;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Peticion de creacion de un producto
 */
@ApiModel(description = "Peticion de creacion de un producto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-24T16:31:00.109-05:00[America/Bogota]")

public class ProductosPSTRs   {
  @JsonProperty("producto")
  private Producto producto = null;

  @JsonProperty("reserva")
  private List<ReservaExterna> reserva;

  public ProductosPSTRs producto(Producto producto) {
    this.producto = producto;
    return this;
  }

  /**
   * Get producto
   * @return producto
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public ProductosPSTRs reserva(List<ReservaExterna> reserva) {
    this.reserva = reserva;
    return this;
  }

  /**
   * Get reserva
   * @return reserva
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<ReservaExterna> getReserva() {
    return reserva;
  }

  public void setReserva(List<ReservaExterna> reserva) {
    this.reserva = reserva;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductosPSTRs productosPSTRs = (ProductosPSTRs) o;
    return Objects.equals(this.producto, productosPSTRs.producto) &&
        Objects.equals(this.reserva, productosPSTRs.reserva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(producto, reserva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductosPSTRs {\n");
    
    sb.append("    producto: ").append(toIndentedString(producto)).append("\n");
    sb.append("    reserva: ").append(toIndentedString(reserva)).append("\n");
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

