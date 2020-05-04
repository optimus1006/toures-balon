package com.touresbalon.api.repository.read;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadDireccionRepository extends JpaRepository<ReadDireccionEntity, Integer>{
	public List<ReadDireccionEntity> findAllByIdCliente(Long idcliente);
	public ReadDireccionEntity findByCodigo(Long codigo);
	public void deleteByIdCliente(Long idcliente);
}
