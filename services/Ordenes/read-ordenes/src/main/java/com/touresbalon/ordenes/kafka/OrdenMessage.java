package com.touresbalon.ordenes.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.touresbalon.ordenes.api.model.Orden;
import com.touresbalon.ordenes.api.model.OrdenItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Orden de pago con los productos del paquete que el cliente va a comprar
 */
public class OrdenMessage extends Orden {

  private Long codigoProducto = null;

  private String uidPago;
  
  @JsonProperty("items")
  private List<OrdenItem> items = null;

  private EnumOrderAction accion = null;

  public OrdenMessage addItem(OrdenItem item) {
    if (this.items == null) {
      this.items = new ArrayList<OrdenItem>();
    }
    this.items.add(item);
    return this;
  }

  @ApiModelProperty(value = "")
    public List<OrdenItem> getItems() {
    return items;
  }

  public void setItems(List<OrdenItem> items) {
    this.items = items;
  }

  public OrdenMessage accion(EnumOrderAction accion) {
    this.accion = accion;
    return this;
  }

  public EnumOrderAction getAccion() {
    return accion;
  }

  public void setAccion(EnumOrderAction accion) {
    this.accion = accion;
  }
  
  public OrdenMessage codigoProducto(Long codigoProducto) {
    this.codigoProducto = codigoProducto;
    return this;
  }

  public Long getCodigoProducto() {
    return codigoProducto;
  }

  public void setCodigoProducto(Long codigoProducto) {
    this.codigoProducto = codigoProducto;
  }

  public String getUidPago() {
    return uidPago;
  }

  public void setUidPago(String uidPago) {
    this.uidPago = uidPago;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Orden {\n");

    sb.append("    codigo: ").append(toIndentedString(getCodigo())).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(getFechaCreacion())).append("\n");
    sb.append("    fechaModificacion: ").append(toIndentedString(getFechaModificacion())).append("\n");
    sb.append("    codigoCliente: ").append(toIndentedString(getCodigoCliente())).append("\n");
    sb.append("    estado: ").append(toIndentedString(getEstado())).append("\n");
    sb.append("    descuento: ").append(toIndentedString(getDescuento())).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(getSubtotal())).append("\n");
    sb.append("    impuestos: ").append(toIndentedString(getImpuestos())).append("\n");
    sb.append("    valortotal: ").append(toIndentedString(getValorTotal())).append("\n");
    sb.append("    moneda: ").append(toIndentedString(getMoneda())).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    factura: ").append(toIndentedString(getFactura())).append("\n");
    sb.append("    accion: ").append(toIndentedString(getAccion())).append("\n");
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
