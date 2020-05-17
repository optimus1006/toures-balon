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
@Table(name = "transporte")
public class TransporteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;

	private Long id_vehiculo;

	private Timestamp fecha_partida;

	private int lugar_partida;

	private Timestamp fecha_llegada;

	private int lugar_llegada;

	private int cantidad_cupos;

	private String convenio;

	private String estado;

	private String tipo;

	private Long valor;

	private Timestamp fecha_creacion;

}
