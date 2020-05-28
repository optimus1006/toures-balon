package com.touresbalon.ordenes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Orden de pago con los productos del paquete que el cliente va a comprar
 */
@ApiModel(description = "Orden de pago con los productos del paquete que el cliente va a comprar")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T05:49:11.682Z[GMT]")
public class Orden   {
  @JsonProperty("codigo")
  private Long codigo = null;

  @JsonProperty("fechaCreacion")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaCreacion = null;

  @JsonProperty("fechaModificacion")
  @JsonDeserialize(using = com.touresbalon.ordenes.util.LocalDateTimeDeserializer.class)
  @JsonSerialize(using = com.touresbalon.ordenes.util.LocalDateTimeSerializer.class)
  private LocalDateTime fechaModificacion = null;

  @JsonProperty("codigoCliente")
  private Long codigoCliente = null;

  @JsonProperty("tarjeta")
  private Tarjeta tarjeta = null;

  /**
   * Muestra el estado de la orden, para verificar en que parte del flujo de atención de la orden  se encuentra.
   */
  public enum EstadoEnum {
    COTIZACION("COTIZACION"),
    
    PAGADA("PAGADA"),
    
    EN_RESERVA("EN_RESERVA"),
    
    CERRADA("CERRADA"),
    
    RECHAZADA("RECHAZADA"),
    
    CANCELADA("CANCELADA");

    private String value;

    EstadoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EstadoEnum fromValue(String text) {
      for (EstadoEnum b : EstadoEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("estado")
  private EstadoEnum estado = null;

  @JsonProperty("descuento")
  private BigDecimal descuento = null;

  @JsonProperty("subtotal")
  private BigDecimal subtotal = null;

  @JsonProperty("impuestos")
  private BigDecimal impuestos = null;

  @JsonProperty("valortotal")
  private BigDecimal valorTotal = null;

  @JsonProperty("moneda")
  private Moneda moneda = null;

  @JsonProperty("transporte")
  @Valid
  private List<Transporte> transporte = null;

  @JsonProperty("hospedaje")
  @Valid
  private List<Hospedaje> hospedaje = null;

  @JsonProperty("evento")
  @Valid
  private List<Evento> evento = null;

  @JsonProperty("factura")
  private Factura factura = null;

  public Orden codigo(Long codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Get codigo
   * @return codigo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public Orden fechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Fecha de creación de la orden.
   * @return fechaCreacion
  **/
  @ApiModelProperty(value = "Fecha de creación de la orden.")

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Orden fechaModificacion(LocalDateTime fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
    return this;
  }

  /**
   * Fecha de modificación de la orden.
   * @return fechaModificacion
  **/
  @ApiModelProperty(value = "Fecha de modificación de la orden.")

  public LocalDateTime getFechaModificacion() {
    return fechaModificacion;
  }

  public void setFechaModificacion(LocalDateTime fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
  }

  public Orden codigoCliente(Long codigoCliente) {
    this.codigoCliente = codigoCliente;
    return this;
  }

  /**
   * Get codigoCliente
   * @return codigoCliente
  **/
  @ApiModelProperty(value = "")
  
    public Long getCodigoCliente() {
    return codigoCliente;
  }

  public void setCodigoCliente(Long codigoCliente) {
    this.codigoCliente = codigoCliente;
  }

  public Orden estado(EstadoEnum estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Muestra el estado de la orden, para verificar en que parte del flujo de atención de la orden  se encuentra.
   * @return estado
  **/
  @ApiModelProperty(value = "Muestra el estado de la orden, para verificar en que parte del flujo de atención de la orden  se encuentra.")
  
    public EstadoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoEnum estado) {
    this.estado = estado;
  }

  public Orden descuento(BigDecimal descuento) {
    this.descuento = descuento;
    return this;
  }

  /**
   * Indica el porcentaje del descuento que pueda tener la orden por el uso de un cup+on o por una promoción vigente.
   * @return descuento
  **/
  @ApiModelProperty(value = "Indica el porcentaje del descuento que pueda tener la orden por el uso de un cup+on o por una promoción vigente.")
  
    public BigDecimal getDescuento() {
    return descuento;
  }

  public void setDescuento(BigDecimal descuento) {
    this.descuento = descuento;
  }

  public Orden subtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Valor calculado de la suma de todo el conjunto de elementos dentro de una orden, previo a aplicar descuentose impuestos.
   * minimum: 0
   * @return subtotal
  **/
  @ApiModelProperty(value = "Valor calculado de la suma de todo el conjunto de elementos dentro de una orden, previo a aplicar descuentose impuestos.")
  
  @Min(0)  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }

  public Orden impuestos(BigDecimal impuestos) {
    this.impuestos = impuestos;
    return this;
  }

  /**
   * Valor calculado de los impuestos a cobrar por la compra realizada.
   * minimum: 0
   * @return impuestos
  **/
  @ApiModelProperty(value = "Valor calculado de los impuestos a cobrar por la compra realizada.")
  
  @Min(0)  public BigDecimal getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(BigDecimal impuestos) {
    this.impuestos = impuestos;
  }

  public Orden valortotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
    return this;
  }

  /**
   * Valor total de la orden a pagar, incluye la suma de la información de los items del producto, impuestos y descuentos a aplicar.
   * minimum: 0
   * @return valortotal
  **/
  @ApiModelProperty(value = "Valor total de la orden a pagar, incluye la suma de la información de los items del producto, impuestos y descuentos a aplicar.")
  
  @Min(0)  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }

  public Orden tarjeta(Tarjeta tarjeta) {
    this.tarjeta = tarjeta;
    return this;
  }

  /**
   * Get tarjeta
   * @return tarjeta
   **/
  @ApiModelProperty(value = "")

  @Valid
  public Tarjeta getTarjeta() {
    return tarjeta;
  }

  public void setTarjeta(Tarjeta tarjeta) {
    this.tarjeta = tarjeta;
  }

  public Orden moneda(Moneda moneda) {
    this.moneda = moneda;
    return this;
  }

  /**
   * Get moneda
   * @return moneda
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Moneda getMoneda() {
    return moneda;
  }

  public void setMoneda(Moneda moneda) {
    this.moneda = moneda;
  }

  public Orden transporte(List<Transporte> transporte) {
    this.transporte = transporte;
    return this;
  }

  public Orden addTransporteItem(Transporte transporteItem) {
    if (this.transporte == null) {
      this.transporte = new ArrayList<Transporte>();
    }
    this.transporte.add(transporteItem);
    return this;
  }

  /**
   * Get transporte
   * @return transporte
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Transporte> getTransporte() {
    return transporte;
  }

  public void setTransporte(List<Transporte> transporte) {
    this.transporte = transporte;
  }

  public Orden hospedaje(List<Hospedaje> hospedaje) {
    this.hospedaje = hospedaje;
    return this;
  }

  public Orden addHospedajeItem(Hospedaje hospedajeItem) {
    if (this.hospedaje == null) {
      this.hospedaje = new ArrayList<Hospedaje>();
    }
    this.hospedaje.add(hospedajeItem);
    return this;
  }

  /**
   * Get hospedaje
   * @return hospedaje
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Hospedaje> getHospedaje() {
    return hospedaje;
  }

  public void setHospedaje(List<Hospedaje> hospedaje) {
    this.hospedaje = hospedaje;
  }

  public Orden evento(List<Evento> evento) {
    this.evento = evento;
    return this;
  }

  public Orden addEventoItem(Evento eventoItem) {
    if (this.evento == null) {
      this.evento = new ArrayList<Evento>();
    }
    this.evento.add(eventoItem);
    return this;
  }

  /**
   * Get evento
   * @return evento
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Evento> getEvento() {
    return evento;
  }

  public void setEvento(List<Evento> evento) {
    this.evento = evento;
  }

  public Orden factura(Factura factura) {
    this.factura = factura;
    return this;
  }

  /**
   * Get factura
   * @return factura
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Factura getFactura() {
    return factura;
  }

  public void setFactura(Factura factura) {
    this.factura = factura;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Orden orden = (Orden) o;
    return Objects.equals(this.codigo, orden.codigo) &&
        Objects.equals(this.fechaCreacion, orden.fechaCreacion) &&
        Objects.equals(this.fechaModificacion, orden.fechaModificacion) &&
        Objects.equals(this.codigoCliente, orden.codigoCliente) &&
        Objects.equals(this.estado, orden.estado) &&
        Objects.equals(this.descuento, orden.descuento) &&
        Objects.equals(this.subtotal, orden.subtotal) &&
        Objects.equals(this.impuestos, orden.impuestos) &&
        Objects.equals(this.valorTotal, orden.valorTotal) &&
        Objects.equals(this.moneda, orden.moneda) &&
        Objects.equals(this.transporte, orden.transporte) &&
        Objects.equals(this.hospedaje, orden.hospedaje) &&
        Objects.equals(this.evento, orden.evento) &&
        Objects.equals(this.factura, orden.factura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, fechaCreacion, fechaModificacion, codigoCliente, estado, descuento, subtotal, impuestos, valorTotal, moneda, transporte, hospedaje, evento, factura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Orden {\n");

    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    fechaModificacion: ").append(toIndentedString(fechaModificacion)).append("\n");
    sb.append("    codigoCliente: ").append(toIndentedString(codigoCliente)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    descuento: ").append(toIndentedString(descuento)).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(subtotal)).append("\n");
    sb.append("    impuestos: ").append(toIndentedString(impuestos)).append("\n");
    sb.append("    valortotal: ").append(toIndentedString(valorTotal)).append("\n");
    sb.append("    moneda: ").append(toIndentedString(moneda)).append("\n");
    sb.append("    transporte: ").append(toIndentedString(transporte)).append("\n");
    sb.append("    hospedaje: ").append(toIndentedString(hospedaje)).append("\n");
    sb.append("    evento: ").append(toIndentedString(evento)).append("\n");
    sb.append("    factura: ").append(toIndentedString(factura)).append("\n");
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
