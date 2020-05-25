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
@Table(name = "acomodacion")
public class AcomodacionEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private double precio;
	
	private Long id_hospedaje;
	
	private int cantidad_cuartos;
	
	private String codigo_externo;

}
