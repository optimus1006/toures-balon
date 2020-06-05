package com.tuboleta.espectaculos.vo;

import java.io.Serializable;
import java.util.Date;

public class RequestRest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3560972546182458142L;
	private String nombreEvento;
	private String clasificacionEvento;
	private Date fechaHora;
	private Date lugar;
	private int numAsientos;
	private String nombre;
	private String identificacion;
	private Double valor;
	private int numReserva;

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getClasificacionEvento() {
		return clasificacionEvento;
	}

	public void setClasificacionEvento(String clasificacionEvento) {
		this.clasificacionEvento = clasificacionEvento;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Date getLugar() {
		return lugar;
	}

	public void setLugar(Date lugar) {
		this.lugar = lugar;
	}

	public int getNumAsientos() {
		return numAsientos;
	}

	public void setNumAsientos(int numAsientos) {
		this.numAsientos = numAsientos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

}