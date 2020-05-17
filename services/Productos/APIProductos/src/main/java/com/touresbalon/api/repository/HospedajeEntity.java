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
@Table(name = "hospedaje")
public class HospedajeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String nombre;
	
	private int tipo_hospedaje;
	
	private int calificacion;
	
	private String direccion;
	
	private String latitud;
	
	private String longitud;
	
	private int id_ciudad;
	
	private String informacion;
	
	private int cantidad_cuartos;
	
	private String id_convenio;
}
