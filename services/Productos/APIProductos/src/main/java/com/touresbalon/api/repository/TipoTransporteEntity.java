package com.touresbalon.api.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tipo_transporte")
public class TipoTransporteEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String descripcion;
	
	private int capacidad_personas;
	
	private int capacidad_equipaje;
	
	private String tipo_capacidad_carga;
}
