package com.touresbalon.api.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.repository.CuartoEntity;
import com.touresbalon.api.repository.CuartoRepository;

@ApplicationScoped
@Transactional
public class CuartoService {

	@Inject
	CuartoRepository cuartoRepository;
	
	public Cuarto crearCuarto(Cuarto cuarto, Long hospedaje) throws HospedajeException{
		CuartoEntity cuartoEntity = new CuartoEntity();
		
		if(cuarto.getNumeroCuarto()!=null) {
			cuartoEntity.setNumero_cuarto(cuarto.getNumeroCuarto());
		}
		else {
			throw new HospedajeException("el numero del cuarto es obligatorio.");
		}
		
		cuartoEntity.setId_hospedaje(hospedaje);
		
		cuartoRepository.save(cuartoEntity);
		
		Cuarto cuartoResponse = new Cuarto();
		cuartoResponse.setId(cuartoEntity.getId());
		
		return cuartoResponse;
	}
}
