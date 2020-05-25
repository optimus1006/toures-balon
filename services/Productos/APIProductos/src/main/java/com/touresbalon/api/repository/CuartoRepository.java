package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CuartoRepository extends CrudRepository<CuartoEntity, Long>{
	public List<CuartoEntity> findById_hospedaje(Long id_hospedaje);
	public List<CuartoEntity> findById_hospedajeAndId_cliente(Long id_hospedaje,Long id_cliente);
}
