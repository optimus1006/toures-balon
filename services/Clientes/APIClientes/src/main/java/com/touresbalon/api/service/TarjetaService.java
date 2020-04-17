package com.touresbalon.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.TarjetaEntity;
import com.touresbalon.api.repository.TarjetaRepository;
import com.touresbalon.service.domain.Tarjeta;

@Service
@Transactional
public class TarjetaService {

	@Autowired
    private TarjetaRepository tarjetaRepository;
	
	public void createTarjeta(Tarjeta tarjeta,Long idCliente) {
		TarjetaEntity tarjetaEntity = new TarjetaEntity(
				tarjeta.getNumero(),
				tarjeta.getTipo().name(),
				tarjeta.getEstado().name(),
				tarjeta.getPrincipal(),
				idCliente
				);
		tarjetaRepository.save(tarjetaEntity);
	}
	public List<Tarjeta> getAllByClienteId(Long idCliente){
		List<TarjetaEntity> tarjetas = tarjetaRepository.findByIdCliente(idCliente);
		return tarjetas.stream().map(tarjetaEntity -> new Tarjeta(
				tarjetaEntity.getNumero(),
				tarjetaEntity.getTipo(),
				tarjetaEntity.getEstado()
				)).collect(Collectors.toList()); 
	}
	public void updateTarjetas(List<Tarjeta> tarjetas) {
		
		
		for(Tarjeta tarjeta:tarjetas) {
			TarjetaEntity tarjetaEntity = tarjetaRepository.findByNumero(tarjeta.getNumero());
			if(tarjeta.getEstado()!=null) {
				tarjetaEntity.setEstado(tarjeta.getEstado().name());
			}
			if(tarjeta.getPrincipal()!=null) {
				tarjetaEntity.setPrincipal(tarjeta.getPrincipal());
			}
			tarjetaRepository.save(tarjetaEntity);
		}
	}
	
	public Tarjeta getbynumber(Long numero) {
		TarjetaEntity tarjetaEntity= tarjetaRepository.findByNumero(numero);
		return new Tarjeta(tarjetaEntity.getNumero(),
				tarjetaEntity.getTipo(),
				tarjetaEntity.getEstado());
	}
	
	public void deleteTarjetasByIdCliente(Long idCliente) {
		tarjetaRepository.deleteByIdCliente(idCliente);
	}
}
