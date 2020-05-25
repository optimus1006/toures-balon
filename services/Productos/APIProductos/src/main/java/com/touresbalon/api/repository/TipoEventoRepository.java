package com.touresbalon.api.repository;

import org.springframework.data.repository.CrudRepository;

public interface TipoEventoRepository extends CrudRepository<TipoEventoEntity, Integer>{

	public TipoEventoEntity findByDescripcion(String descripcion);
}
