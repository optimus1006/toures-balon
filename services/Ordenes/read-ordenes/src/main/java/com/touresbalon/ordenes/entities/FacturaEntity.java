package com.touresbalon.ordenes.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Información requerida apra mostrar una factura.
 */
@Entity
@Table(name = "factura")
public class FacturaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private LocalDateTime fechaCreacion;

    private BigDecimal valorTotal;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "factura", fetch = FetchType.LAZY)
    private CodigoBarrasEntity codigoBarras;

    @JoinColumn(name = "orden")
    @OneToOne
    private OrdenEntity orden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CodigoBarrasEntity getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(CodigoBarrasEntity codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public OrdenEntity getOrden() {
        return orden;
    }

    public void setOrden(OrdenEntity orden) {
        this.orden = orden;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
