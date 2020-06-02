package com.avianca.vuelos.vo;

public class Pago {

	protected int numVuelo;
	protected String mensaje;

	public Pago(int numVuelo, String mensaje) {
		this.numVuelo = numVuelo;
		this.mensaje = mensaje;
	}

	public int getNumVuelo() {
		return numVuelo;
	}

	public void setNumVuelo(int numVuelo) {
		this.numVuelo = numVuelo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
