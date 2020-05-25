package com.touresbalon.ordenes.restclient.productos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Peticion de creacion de un producto
 */
@ApiModel(description = "Peticion de creacion de un producto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class ProductosPSTRq   {
  @JsonProperty("producto")
  private Producto producto = null;

  public ProductosPSTRq producto(Producto producto) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductosPSTRq productosPSTRq = (ProductosPSTRq) o;
    return Objects.equals(this.producto, productosPSTRq.producto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(producto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductosPSTRq {\n");
    
    sb.append("    producto: ").append(toIndentedString(producto)).append("\n");
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

