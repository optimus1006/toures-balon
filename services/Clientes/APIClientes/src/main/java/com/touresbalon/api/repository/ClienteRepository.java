package com.touresbalon.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	public ClienteEntity findByIdentificacion(String identificacion);
	public ClienteEntity findById(Long id);
	public void deleteById(Long id);
}
