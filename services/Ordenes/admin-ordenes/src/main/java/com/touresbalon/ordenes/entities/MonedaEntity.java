package com.touresbalon.ordenes.entities;;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Moneda
 */
@Entity
@Table(name = "moneda")
public class MonedaEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal amount;
  private String currency;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orden")
  private OrdenEntity orden;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public OrdenEntity getOrden() {
    return orden;
  }

  public void setOrden(OrdenEntity orden) {
    this.orden = orden;
  }
}
