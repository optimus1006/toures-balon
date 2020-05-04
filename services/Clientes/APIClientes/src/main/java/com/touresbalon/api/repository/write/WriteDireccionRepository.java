package com.touresbalon.api.repository.write;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteDireccionRepository extends JpaRepository<WriteDireccionEntity, Integer>{
	public List<WriteDireccionEntity> findAllByIdCliente(Long idcliente);
	public WriteDireccionEntity findByCodigo(Long codigo);
	public void deleteByIdCliente(Long idcliente);
}
