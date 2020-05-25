package com.touresbalon.api.repository;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "producto_detalle")
public class ProductoDetalleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long id_transporte;
	
	private Long id_hospedaje;
	
	private Long id_evento;
	
	private Timestamp fecha_registro;
	
	private Timestamp fecha_ultimo_movimiento;
	
	private Long id_producto;
	
	private int asientos_evento;
	
	private int asientos_transporte;
	
	private int cuartos_hospedaje;

}
