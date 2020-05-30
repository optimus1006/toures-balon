package com.touresbalon.ordenes.entities;

import com.touresbalon.ordenes.util.EnumTipoProducto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrdenItemEntity extends PanacheEntityBase {

	@Column(nullable = true, length = 200)
	private String descripcion;

	@JoinColumn(name = "order_id")
	@ManyToOne
	private OrdenEntity orden;

	@Column(nullable = false)
	private Long codigo;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = true)
	private BigDecimal precio;

	@Column(nullable = true)
	private Integer cantidad;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoProducto tipoProducto;

	public OrdenEntity getOrden() {
		return orden;
	}

	public void setOrden(OrdenEntity orden) {
		this.orden = orden;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public EnumTipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(EnumTipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrdenItemEntity{" +
				"descripcion='" + descripcion + '\'' +
				", id=" + id +
				", codigo=" + codigo +
				", precio=" + precio +
				", cantidad=" + cantidad +
				", tipoProducto=" + tipoProducto +
				'}';
	}
}
