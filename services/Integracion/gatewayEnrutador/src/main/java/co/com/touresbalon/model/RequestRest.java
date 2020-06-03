package co.com.touresbalon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRest {

	@JsonProperty("origen")
	private String origen;

	@JsonProperty("destino")
	private String destino;

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

}
