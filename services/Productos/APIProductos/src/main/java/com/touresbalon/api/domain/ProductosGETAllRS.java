package com.touresbalon.api.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.touresbalon.api.domain.Producto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Respuesta de todos los productos existentes
 */
@ApiModel(description = "Respuesta de todos los productos existentes")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-10T10:11:38.301-05:00[America/Bogota]")

public class ProductosGETAllRS   {
  @JsonProperty("productos")
  @Valid
  private List<Producto> productos = null;

  public ProductosGETAllRS productos(List<Producto> productos) {
    this.productos = productos;
    return this;
  }

  public ProductosGETAllRS addProductosItem(Producto productosItem) {
    if (this.productos == null) {
      this.productos = new ArrayList<>();
    }
    this.productos.add(productosItem);
    return this;
  }

  /**
   * Get productos
   * @return productos
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Producto> getProductos() {
    return productos;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductosGETAllRS productosGETAllRS = (ProductosGETAllRS) o;
    return Objects.equals(this.productos, productosGETAllRS.productos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductosGETAllRS {\n");
    
    sb.append("    productos: ").append(toIndentedString(productos)).append("\n");
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

