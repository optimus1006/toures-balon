package com.touresbalon.api.repository.read;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadTarjetaRepository extends JpaRepository<ReadTarjetaEntity, Integer>{
	public List<ReadTarjetaEntity> findByIdCliente(Long idcliente);
	public ReadTarjetaEntity findByNumero(Long numero);
	public void deleteByIdCliente(Long idcliente);
	
}
