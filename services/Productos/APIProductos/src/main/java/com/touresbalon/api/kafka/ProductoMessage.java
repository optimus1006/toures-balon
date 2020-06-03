package com.touresbalon.api.kafka;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.api.domain.Producto;
import com.touresbalon.api.domain.ProductosPSTRq;

import io.swagger.annotations.ApiModelProperty;

public class ProductoMessage {

	@JsonProperty("producto")
	  private Producto producto = null;

	  public ProductoMessage producto(Producto producto) {
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


//	  @Override
//	  public boolean equals(java.lang.Object o) {
//	    if (this == o) {
//	      return true;
//	    }
//	    if (o == null || getClass() != o.getClass()) {
//	      return false;
//	    }
//	    ProductosPSTRq productosPSTRq = (ProductosPSTRq) o;
//	    return Objects.equals(this.producto, productosPSTRq.producto);
//	  }

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
	  private String toIndentedString(java.lang.Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
}
