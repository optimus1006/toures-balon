package com.touresbalon.ordenes.entities;

import com.touresbalon.ordenes.api.model.Orden;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Información requerida apra mostrar una orden
 */
@Entity
@Table(name = "orden")
public class OrdenEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long codigo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPago;
    private LocalDateTime fechaModificacion;
    private Long codigoCliente;
    private Long codigoProducto;

    @Enumerated(EnumType.STRING)
    private Orden.EstadoEnum estado;

    private BigDecimal descuento;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal valorTotal;
    private String uidPago;

    @org.hibernate.annotations.Type(type="yes_no")
    @Column(name = "enviado", nullable = false)
    boolean  enviado;

    @OneToMany(mappedBy = "orden")
    private List<OrdenItemEntity> items;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orden", fetch = FetchType.LAZY)
    private MonedaEntity moneda;

    //private List<TransporteEntity> transporte;
    //private List<HospedajeEntity> hospedaje;
    //private List<EventoEntity> evento;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "orden", fetch = FetchType.LAZY)
    private FacturaEntity factura;

    @OneToMany(mappedBy = "orden", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TarjetaEntity> tarjetas;

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

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
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

    public List<OrdenItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrdenItemEntity> items) {
        this.items = items;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public List<TarjetaEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public String getUidPago() {
        return uidPago;
    }

    public void setUidPago(String uidPago) {
        this.uidPago = uidPago;
    }
}
