package com.hilton.hoteles.vo;

public class Response {

	protected int numReserva;
	protected String mensaje;

	public Response(int numReserva, String mensaje) {
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
