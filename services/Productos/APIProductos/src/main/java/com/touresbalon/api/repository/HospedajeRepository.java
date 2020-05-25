package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HospedajeRepository extends CrudRepository<HospedajeEntity, Long>{
	public List<HospedajeEntity> findById_convenio(String id_convenio);
	public List<HospedajeEntity> findByNombre(String nombre);
	public List<HospedajeEntity> findByCalificacion(int calificacion);
	public List<HospedajeEntity> findByTipo_hospedaje(int tipo_hospedaje);
	public List<HospedajeEntity> findById_ciudad(Long id_ciudad);
}
