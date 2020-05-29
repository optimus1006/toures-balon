package com.touresbalon.api.repository.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ReadCategoriaRepository extends JpaRepository<ReadCategoriaEntity, Integer>{
	
}
