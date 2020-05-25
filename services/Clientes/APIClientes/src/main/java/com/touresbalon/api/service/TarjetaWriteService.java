package com.touresbalon.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.write.WriteTarjetaEntity;
import com.touresbalon.api.repository.write.WriteTarjetaRepository;
import com.touresbalon.service.domain.Tarjeta;

@Service
@Transactional
public class TarjetaWriteService {
	@Autowired
    private WriteTarjetaRepository tarjetaRepository;
	
	public void createTarjeta(Tarjeta tarjeta,Long idCliente) {
		WriteTarjetaEntity tarjetaEntity = new WriteTarjetaEntity(
				tarjeta.getNumero(),
				tarjeta.getTipo().name(),
				tarjeta.getEstado().name(),
				tarjeta.getPrincipal(),
				idCliente
				);
		tarjetaRepository.save(tarjetaEntity);
	}
}
