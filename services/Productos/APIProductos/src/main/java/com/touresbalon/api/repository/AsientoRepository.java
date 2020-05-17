package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AsientoRepository extends CrudRepository<AsientoEntity, Long>{
	public AsientoEntity findAsientoByIdAndId_transporte(Long id,Long id_transporte);
	public List<AsientoEntity> findAsientoById_transporte(Long id_transporte);
	public List<AsientoEntity> findAsientoById_transporteAndId_cliente(Long id_transporte,Long id_cliente);
}
