package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LocalidadRepository extends CrudRepository<LocalidadEntity, Long>{

	public List<LocalidadEntity> findById_evento(Long id_evento);
}
