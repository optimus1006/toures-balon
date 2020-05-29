package com.touresbalon.api.repository.read;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "categoria")
public class ReadCategoriaEntity {
	
	public ReadCategoriaEntity() {
	}

	@Id
	@Column(name="codigo")
	private Integer codigo;

	@Column(name="nombre")
	private String nombre;
}
