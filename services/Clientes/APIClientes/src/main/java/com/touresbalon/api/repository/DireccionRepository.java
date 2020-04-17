package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer>{
	public List<DireccionEntity> findAllByIdCliente(Long idcliente);
	public DireccionEntity findByCodigo(Long codigo);
	public void deleteByIdCliente(Long idcliente);
}
