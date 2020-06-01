package com.touresbalon.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sillas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sillas {

	private int numViaje;
	private String ciudadOrigen;
	private String ciudadDestino;
	private String numSilla;
	private Date horaSalida;

	public int getNumViaje() {
		return numViaje;
	}

	public void setNumViaje(int numViaje) {
		this.numViaje = numViaje;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getNumSilla() {
		return numSilla;
	}

	public void setNumSilla(String numSilla) {
		this.numSilla = numSilla;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

}
