package com.touresbalon.api.repository.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadClienteRepository extends JpaRepository<ReadClienteEntity, Integer> {
	public ReadClienteEntity findByIdentificacion(String identificacion);
	public ReadClienteEntity findById(Long id);
	public void deleteById(Long id);
}
