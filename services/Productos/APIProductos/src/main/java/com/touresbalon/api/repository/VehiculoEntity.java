package com.touresbalon.api.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vehiculo")
public class VehiculoEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="modelo")
	private int modelo;
	
	@Column(name="referencia")
	private String referencia;
	
	@Column(name="tipo_vehiculo")
	private int tipo_vehiculo;
}
