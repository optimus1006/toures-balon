package com.touresbalon.api.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

/**
 * Información requerida apra mostrar un código de barras en una factura.
 */
@Entity
@Table(name = "codigo_barras")
public class CodigoBarrasEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String tipo;
  private String texto;

  @OneToOne
  @JoinColumn(name = "factura")
  private FacturaEntity factura;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public FacturaEntity getFactura() {
    return factura;
  }

  public void setFactura(FacturaEntity factura) {
    this.factura = factura;
  }
}
