package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AsientoEventoRepository extends CrudRepository<AsientoEventoEntity, Long>{

	public AsientoEventoEntity findAsientoByIdAndId_localidad(Long id,Long id_localidad);
	public List<AsientoEventoEntity> findAsientoById_localidad(Long id_localidad);
	public List<AsientoEventoEntity> findAsientoById_localidadAndId_cliente(Long id_localidad,Long id_cliente);
	public List<AsientoEventoEntity> findById_localidadAndId_producto(Long id_localidad, Long id_producto);
	public List<AsientoEventoEntity> findById_producto(Long id_producto);
}
