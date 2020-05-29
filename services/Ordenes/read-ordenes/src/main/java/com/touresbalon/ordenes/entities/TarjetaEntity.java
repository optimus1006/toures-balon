package com.touresbalon.ordenes.entities;

import com.touresbalon.ordenes.api.model.Tarjeta;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "tarjeta")
public class TarjetaEntity extends PanacheEntityBase {

	public TarjetaEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="numero")
	private String numero;

	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private Tarjeta.TipoEnum tipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orden")
	private OrdenEntity orden;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Tarjeta.TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(Tarjeta.TipoEnum tipo) {
		this.tipo = tipo;
	}

	public OrdenEntity getOrden() {
		return orden;
	}

	public void setOrden(OrdenEntity orden) {
		this.orden = orden;
	}
}
