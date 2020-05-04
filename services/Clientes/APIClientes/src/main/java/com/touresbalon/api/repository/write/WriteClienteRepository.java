package com.touresbalon.api.repository.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface WriteClienteRepository extends JpaRepository<WriteClienteEntity, Integer> {
	public WriteClienteEntity findByIdentificacion(String identificacion);
	public WriteClienteEntity findById(Long id);
	public void deleteById(Long id);
}
