package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AcomodacionRepository extends CrudRepository<AcomodacionEntity, Long>{
	public List<AcomodacionEntity> findById_hospedaje(Long id_hospedaje);
}
