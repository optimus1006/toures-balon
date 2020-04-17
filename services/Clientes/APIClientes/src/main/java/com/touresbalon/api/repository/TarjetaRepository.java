package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Integer>{
	public List<TarjetaEntity> findByIdCliente(Long idcliente);
	public TarjetaEntity findByNumero(Long numero);
	public void deleteByIdCliente(Long idcliente);
	
}
