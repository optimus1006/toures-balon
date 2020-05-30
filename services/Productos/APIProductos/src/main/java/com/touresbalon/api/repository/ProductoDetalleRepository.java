package com.touresbalon.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductoDetalleRepository extends CrudRepository<ProductoDetalleEntity, Long>{
	List<ProductoDetalleEntity> findById_producto(Long id_producto);
}
