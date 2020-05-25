package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ImagenRepository extends CrudRepository<ImagenEntity, Long>{

	public List<ImagenEntity> findByTipo_imagenAndId_hospedaje(String tipo_imagen,Long id_hospedaje);
	public List<ImagenEntity> findByTipo_imagenAndId_evento(String tipo_imagen,Long id_evento);
}
