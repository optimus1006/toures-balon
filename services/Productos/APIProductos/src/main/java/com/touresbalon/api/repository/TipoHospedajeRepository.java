package com.touresbalon.api.repository;

import org.springframework.data.repository.CrudRepository;

public interface TipoHospedajeRepository extends CrudRepository<TipoHospedajeEntity, Integer>{
	public TipoHospedajeEntity findByDescripcion(String descripcion);
}
