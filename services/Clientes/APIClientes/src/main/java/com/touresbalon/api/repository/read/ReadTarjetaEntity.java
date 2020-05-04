package com.touresbalon.api.repository.read;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class ReadTarjetaEntity {
	
	public ReadTarjetaEntity() {
	}
	
	public ReadTarjetaEntity(Long numero, String tipo, String estado, Boolean principal, Long idCliente) {
		this.numero=numero;
		this.tipo=tipo;
		this.estado=estado;
		this.principal=principal;
		this.idCliente=idCliente;		
	}

	@Id
	@Column(name="numero")
	private Long numero;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="principal")
	private boolean principal;
	
	@Column(name="idcliente")
	private Long idCliente;
}
