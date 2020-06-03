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
@Table(name = "asiento_evento")
public class AsientoEventoEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long id_localidad;
	
	private Long id_cliente;
	
	private String numero;
	
	private Timestamp fecha_reserva;
	
	private Long id_producto;
}
