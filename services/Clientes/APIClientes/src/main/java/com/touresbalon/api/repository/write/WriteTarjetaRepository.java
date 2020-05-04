package com.touresbalon.api.repository.write;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteTarjetaRepository extends JpaRepository<WriteTarjetaEntity, Integer>{
	public List<WriteTarjetaEntity> findByIdCliente(Long idcliente);
	public WriteTarjetaEntity findByNumero(Long numero);
	public void deleteByIdCliente(Long idcliente);
	
}
