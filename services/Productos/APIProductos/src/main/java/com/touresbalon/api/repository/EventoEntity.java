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
@Table(name = "evento")
public class EventoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private Timestamp fecha;
	
	private String hora;
	
	private String zona_horaria;
	
	private int cantidad;
	
	private String estado;
	
	private int tipo;
	
	private Long imagen_principal;
	
	private String direccion;
	
	private String latitud;
	
	private String longitud;
	
	private int id_ciudad;
	
	private String codigo_externo;
}
