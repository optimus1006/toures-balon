package com.hilton.hoteles.vo;

public class Response {

	protected int numHabitacion;
	protected String mensaje;

	public Response(int numHabitacion, String mensaje) {
		this.numHabitacion = numHabitacion;
		this.mensaje = mensaje;
	}

	public int getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
