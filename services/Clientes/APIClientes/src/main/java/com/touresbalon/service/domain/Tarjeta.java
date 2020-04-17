package com.touresbalon.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Contiene la información de la tarjeta
 */
@ApiModel(description = "Contiene la información de la tarjeta")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-04-09T13:17:10.375-05:00[America/Bogota]")

public class Tarjeta {
	@JsonProperty("numero")
	private Long numero;

	/**
	 * Gets or Sets tipo
	 */
	public enum TipoEnum {
		VISA("VISA"),

		MASTER_CARD("MASTER_CARD");

		private String value;

		TipoEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TipoEnum fromValue(String value) {
			for (TipoEnum b : TipoEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	@JsonProperty("tipo")
	private TipoEnum tipo;

	/**
	 * Gets or Sets estado
	 */
	public enum EstadoEnum {
		ACTIVA("ACTIVA"),

		INACTIVA("INACTIVA");

		private String value;

		EstadoEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static EstadoEnum fromValue(String value) {
			for (EstadoEnum b : EstadoEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	@JsonProperty("estado")
	private EstadoEnum estado;

	@JsonProperty("principal")
	private Boolean principal = false;

	public Tarjeta(Long numero, String tipo, String estado) {
		this.numero = numero;
		this.tipo = TipoEnum.valueOf(tipo);
		this.estado = EstadoEnum.valueOf(estado);
	}

	public Tarjeta numero(Long numero) {
		this.numero = numero;
		return this;
	}

	/**
	 * Get numero
	 * 
	 * @return numero
	 */
	@ApiModelProperty(value = "")

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Tarjeta tipo(TipoEnum tipo) {
		this.tipo = tipo;
		return this;
	}

	/**
	 * Get tipo
	 * 
	 * @return tipo
	 */
	@ApiModelProperty(value = "")

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	public Tarjeta estado(EstadoEnum estado) {
		this.estado = estado;
		return this;
	}

	/**
	 * Get estado
	 * 
	 * @return estado
	 */
	@ApiModelProperty(value = "")

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Tarjeta principal(Boolean principal) {
		this.principal = principal;
		return this;
	}

	/**
	 * Get principal
	 * 
	 * @return principal
	 */
	@ApiModelProperty(value = "")

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tarjeta tarjeta = (Tarjeta) o;
		return Objects.equals(this.numero, tarjeta.numero) && Objects.equals(this.tipo, tarjeta.tipo)
				&& Objects.equals(this.estado, tarjeta.estado) && Objects.equals(this.principal, tarjeta.principal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, tipo, estado, principal);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");

		sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
		sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
		sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
