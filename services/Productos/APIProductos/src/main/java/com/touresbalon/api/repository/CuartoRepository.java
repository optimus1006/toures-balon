package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CuartoRepository extends CrudRepository<CuartoEntity, Long>{
	public List<CuartoEntity> findById_acomodacion(Long id_acomodacion);
	public List<CuartoEntity> findById_acomodacionAndId_cliente(Long id_acomodacion,Long id_cliente);
	public List<CuartoEntity> findById_acomodacionAndId_producto(Long id_acomodacion, Long id_producto);
	public List<CuartoEntity> findById_producto(Long id_producto);
}
