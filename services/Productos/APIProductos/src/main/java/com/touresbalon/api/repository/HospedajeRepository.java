package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HospedajeRepository extends CrudRepository<HospedajeEntity, Long>{
	public List<HospedajeEntity> findById_convenio(String id_convenio);
}
