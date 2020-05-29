package com.touresbalon.api.repository.write;



import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "cliente")
public class WriteClienteEntity {


	public WriteClienteEntity(String identificacion, String nombres, String apellidos, String telefono, String email,
			String celular, @Valid @DecimalMin("1") int estado, @Valid Integer categoria,
			String tipoIdentificacion, Timestamp fechaRegistro) {
		this.identificacion=identificacion;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.email=email;
		this.telefono=telefono;
		this.celular=celular;
		this.estado=estado;
		this.categoria=categoria;
		this.tipoIdentificacion=tipoIdentificacion; 
		this.fechaRegistro=fechaRegistro;	
	}
	
	public WriteClienteEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="identificacion")
	private String identificacion;

	@Column(name="nombres")
	private String nombres;

	@Column(name="apellidos")
	private String apellidos;

	@Column(name="email")
	private String email;

	@Column(name="telefono")
	private String telefono;

	@Column(name="celular")
	private String celular;

	@Column(name="estado")
	private int estado;

	@Column(name="categoria")
	private Integer categoria;

	@Column(name="tipoidentificacion")
	private String tipoIdentificacion;
	
	@Column(name="fecharegistro")
	private Timestamp fechaRegistro;
}
