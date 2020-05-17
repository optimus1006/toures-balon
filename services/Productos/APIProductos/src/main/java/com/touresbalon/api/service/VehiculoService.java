package com.touresbalon.api.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.google.inject.Inject;
import com.touresbalon.api.domain.TipoTransporte;
import com.touresbalon.api.domain.Vehiculo;
import com.touresbalon.api.repository.VehiculoEntity;
import com.touresbalon.api.repository.VehiculoRepository;

@ApplicationScoped
@Transactional
public class VehiculoService {

	@Inject
	VehiculoRepository vehiculoRepository;
	
	public Vehiculo buscarPorId(Long id) {
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(id); 
		if(vehiculoEntity.isPresent()) {
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setId(vehiculoEntity.get().getId());
			vehiculo.setMarca(vehiculoEntity.get().getMarca());
			vehiculo.setModelo(vehiculoEntity.get().getModelo());
			vehiculo.setReferencia(vehiculoEntity.get().getReferencia());
			TipoTransporte tipoTransporte = new TipoTransporte();
			tipoTransporte.setId(vehiculoEntity.get().getTipo_vehiculo());
			vehiculo.setTipoVehiculo(tipoTransporte);
			return vehiculo;
		}
		else {
			return null;
		}
	}
}
