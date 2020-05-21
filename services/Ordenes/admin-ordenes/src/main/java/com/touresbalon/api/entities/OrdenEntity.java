package com.touresbalon.api.entities;;

import com.touresbalon.api.domain.Orden;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Información requerida apra mostrar una orden
 */
@Entity
@Table(name = "orden")
public class OrdenEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(insertable = false, updatable = false, columnDefinition="serial")
  private Long codigo;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaModificacion;
  private Integer codigoCliente;

  @Enumerated(EnumType.STRING)
  private Orden.EstadoEnum estado;

  private BigDecimal descuento;
  private BigDecimal subtotal;
  private BigDecimal impuestos;
  private BigDecimal valorTotal;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "orden", fetch = FetchType.LAZY)
  private MonedaEntity moneda;

  //private List<TransporteEntity> transporte;
  //private List<HospedajeEntity> hospedaje;
  //private List<EventoEntity> evento;

  @OneToOne(cascade = CascadeType.MERGE, mappedBy = "orden", fetch = FetchType.LAZY)
  private FacturaEntity factura;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public LocalDateTime getFechaModificacion() {
    return fechaModificacion;
  }

  public void setFechaModificacion(LocalDateTime fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
  }

  public Integer getCodigoCliente() {
    return codigoCliente;
  }

  public void setCodigoCliente(Integer codigoCliente) {
    this.codigoCliente = codigoCliente;
  }

  public Orden.EstadoEnum getEstado() {
    return estado;
  }

  public void setEstado(Orden.EstadoEnum estado) {
    this.estado = estado;
  }

  public BigDecimal getDescuento() {
    return descuento;
  }

  public void setDescuento(BigDecimal descuento) {
    this.descuento = descuento;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }

  public BigDecimal getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(BigDecimal impuestos) {
    this.impuestos = impuestos;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }

  public MonedaEntity getMoneda() {
    return moneda;
  }

  public void setMoneda(MonedaEntity moneda) {
    this.moneda = moneda;
  }

  public FacturaEntity getFactura() {
    return factura;
  }

  public void setFactura(FacturaEntity factura) {
    this.factura = factura;
  }
}
