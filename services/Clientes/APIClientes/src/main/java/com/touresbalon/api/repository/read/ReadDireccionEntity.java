package com.touresbalon.api.repository.read;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "direccion")
public class ReadDireccionEntity {
	
	public ReadDireccionEntity(@Size(max = 254) String direccion, String tipo, int idPais,
			int idCiudad, @Valid int idEstado, Date fechaCreacion,
			Long idCliente, Float latitud, Float longitud) {
		this.direccion=direccion;
		this.tipo=tipo;
		this.idPais=idPais;
		this.idCiudad=idCiudad;
		this.idEstado=idEstado;
		this.fechaCreacion=fechaCreacion;
		this.idCliente=idCliente;
		this.latitud=latitud.toString();
		this.longitud=longitud.toString();
	}

	public ReadDireccionEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name= "direccion")
	private String direccion;
	
	@Column(name= "tipo")
	private String tipo;
	
	@Column(name= "pais")
	private int idPais;
	
	@Column(name= "ciudad")
	private int idCiudad;
	
	@Column(name= "estado")
	private int idEstado;
	
	@Column(name= "fechacreacion")
	private Date fechaCreacion;
	
	@Column(name= "idcliente")
	private Long idCliente;
	
	@Column(name= "latitud")
	private String latitud;
	
	@Column(name= "longitud")
	private String longitud;
	
	

}
