package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<ProductoEntity, Long>{
	List<ProductoEntity> findById_cliente(Long id_cliente);
	List<ProductoEntity> findById_clienteAndId(Long id_cliente,Long id);
}
