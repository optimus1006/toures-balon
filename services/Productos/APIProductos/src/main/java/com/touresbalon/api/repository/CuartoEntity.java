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
@Table(name = "cuarto")
public class CuartoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero_cuarto;
	
	private Long id_cliente;
	
	private Timestamp fecha_inicio;
	
	private Timestamp fecha_fin;
	
	private Timestamp fecha_reserva;
	
	private Long id_acomodacion;
	
	private Long id_producto;
}
