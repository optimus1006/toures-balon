package com.touresbalon.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.EventoException;
import com.touresbalon.api.domain.Localidad;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.repository.LocalidadEntity;
import com.touresbalon.api.repository.LocalidadRepository;

@ApplicationScoped
@Transactional
public class LocalidadService {

	@Inject
	LocalidadRepository localidadRepository;

	public Localidad crearLocalidad(Localidad localidad, Long idEvento) throws EventoException {
		LocalidadEntity localidadEntity = new LocalidadEntity();

		if (localidad.getAforo() != null) {
			localidadEntity.setAforo(localidad.getAforo());
		} else {
			throw new EventoException("El aforo de la localidad es obligatorio");
		}

		if (localidad.getNombre() != null) {
			localidadEntity.setNombre(localidad.getNombre());
		} else {
			throw new EventoException("El nombre de la localidad es obligatorio");
		}

		if (localidad.getPrecio() != null) {
			localidadEntity.setPrecio(localidad.getPrecio());
		} else {
			throw new EventoException("El precio de la localidad es obligatorio");
		}

		localidadEntity.setId_evento(idEvento);
		
		if (localidad.getCodigoExterno() != null) {
			localidadEntity.setCodigo_externo(localidad.getCodigoExterno());
		} else {
			throw new TransporteException("Debe especificar el codigo de homologacion de la localidad.");
		}

		localidadRepository.save(localidadEntity);

		localidad.setId(localidadEntity.getId());

		return localidad;
	}

	public Localidad actualizar(Localidad localidad) {
		Optional<LocalidadEntity> localidadEntity = localidadRepository.findById(localidad.getId());
		
		if(localidadEntity.isPresent()) {
			if (localidad.getAforo() != null) {
				localidadEntity.get().setAforo(localidad.getAforo());
			}

			if (localidad.getNombre() != null) {
				localidadEntity.get().setNombre(localidad.getNombre());
			} 

			if (localidad.getPrecio() != null) {
				localidadEntity.get().setPrecio(localidad.getPrecio());
			}

			localidadRepository.save(localidadEntity.get());

			return localidad;
		}
		else {
			throw new EventoException("La localidad con el id " + localidad.getId() + " no existe.");
		}
	}
	
	public List<Localidad> buscarPorIdEvento(Long idEvento) throws EventoException{
		List<LocalidadEntity> localidadesEntity = localidadRepository.findById_evento(idEvento);
		List<Localidad> localidades = new ArrayList<>();
		
		if(localidadesEntity.size()>0) {
			for(LocalidadEntity localidadEntity : localidadesEntity) {
				Localidad localidad = new Localidad();
				localidad.setAforo(localidadEntity.getAforo());
				localidad.setId(localidadEntity.getId());
				localidad.setNombre(localidadEntity.getNombre());
				localidad.setPrecio(localidadEntity.getPrecio());
				localidad.setCodigoExterno(localidadEntity.getCodigo_externo());
				localidades.add(localidad);
			}
			
			return localidades;
		}
		else {
			throw new EventoException("La localidad con el id " + idEvento + " no existe.");
		}
	}
	
	public Localidad buscarPorId(Long id) throws EventoException{
		Optional<LocalidadEntity> localidadEntity = localidadRepository.findById(id);
		Localidad localidad = new Localidad();
		
		if(localidadEntity.isPresent()) {
			localidad.setAforo(localidadEntity.get().getAforo());
			localidad.setId(localidadEntity.get().getId());
			localidad.setNombre(localidadEntity.get().getNombre());
			localidad.setPrecio(localidadEntity.get().getPrecio());
			return localidad;
		}
		else {
			throw new EventoException("La localidad con el id " + id + " no existe.");
		}
	}

}
