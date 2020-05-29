package com.avianca.vuelos.vo;

public class Response {

	protected int numVuelo;
	protected String mensaje;

	public Response(int numVuelo, String mensaje) {
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
