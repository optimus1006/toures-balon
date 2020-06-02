package com.avianca.vuelos.vo;

public class Reserva {

	protected int numReserva;
	protected String mensaje;

	public Reserva(int numReserva, String mensaje) {
		this.numReserva = numReserva;
		this.mensaje = mensaje;
	}

	public int getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
