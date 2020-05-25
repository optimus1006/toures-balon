package com.touresbalon.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Acomodacion;
import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.repository.AcomodacionEntity;
import com.touresbalon.api.repository.AcomodacionRepository;

@ApplicationScoped
@Transactional
public class AcomodacionService {

	@Inject
	AcomodacionRepository acomodacionRepository;

	public Acomodacion crearAcomodacion(Acomodacion acomodacion,Long idHospedaje) throws HospedajeException {
		AcomodacionEntity acomodacionEntity = new AcomodacionEntity();

		if (acomodacion.getNombre() != null) {
			acomodacionEntity.setNombre(acomodacion.getNombre());
		} else {
			throw new HospedajeException("El nombre es obligatorio");
		}
		if (acomodacion.getCantidadCuartos() != null) {
			acomodacionEntity.setCantidad_cuartos(acomodacion.getCantidadCuartos());
		}
		if (acomodacion.getDescripcion() != null) {
			acomodacionEntity.setDescripcion(acomodacion.getDescripcion());
		}
		if (acomodacion.getPrecio() != 0) {
			acomodacionEntity.setPrecio(acomodacion.getPrecio());
		} else {
			throw new HospedajeException("El precio del hospedaje es obligatorio");
		}
		
		if (acomodacion.getCodigoExterno() != null) {
			acomodacionEntity.setCodigo_externo(acomodacion.getCodigoExterno());
		} else {
			throw new TransporteException("Debe especificar el codigo de homologacion de la acomodacion.");
		}
		acomodacionEntity.setId_hospedaje(idHospedaje);
		acomodacionRepository.save(acomodacionEntity);

		Acomodacion acomodacionResponse = new Acomodacion();

		acomodacionResponse.setId(acomodacionEntity.getId());

		return acomodacionResponse;
	}

	public Acomodacion actualizarAcomodacion(Acomodacion acomodacion,Long acomodacionId) {
		Optional<AcomodacionEntity> acomodacionEntity = acomodacionRepository.findById(acomodacionId);

		if(acomodacionEntity.isPresent()) {
			if (acomodacion.getNombre() != null) {
				acomodacionEntity.get().setNombre(acomodacion.getNombre());
			}
			
			if (acomodacion.getCantidadCuartos() != null) {
				acomodacionEntity.get().setCantidad_cuartos(acomodacion.getCantidadCuartos());
			}
			if (acomodacion.getDescripcion() != null) {
				acomodacionEntity.get().setDescripcion(acomodacion.getDescripcion());
			}
			if (acomodacion.getPrecio() != 0) {
				acomodacionEntity.get().setPrecio(acomodacion.getPrecio());
			}
			
			acomodacionRepository.save(acomodacionEntity.get());

			Acomodacion acomodacionResponse = new Acomodacion();

			acomodacionResponse.setId(acomodacionEntity.get().getId());

			return acomodacionResponse;
		}
		else {
			throw new HospedajeException("La acomodacion no existe");
		}
	}
	
	public Acomodacion buscarAcomodacionPorId(Long idAcomodacion) {
		Acomodacion acomodacion = new Acomodacion();
		Optional<AcomodacionEntity> acomodacionEntity = acomodacionRepository.findById(idAcomodacion);
		
		if(acomodacionEntity.isPresent()) {
			acomodacion.setCantidadCuartos(acomodacionEntity.get().getCantidad_cuartos());
			acomodacion.setDescripcion(acomodacionEntity.get().getDescripcion());
			acomodacion.setNombre(acomodacionEntity.get().getNombre());
			acomodacion.setPrecio(acomodacionEntity.get().getPrecio());
			acomodacion.setId(acomodacionEntity.get().getId());
			return acomodacion;
		}
		else {
			throw new HospedajeException("La acomodacion no existe");
		}
	}
	
	public List<Acomodacion> listarAcomodaciones(Long idHospedaje){
		List<Acomodacion> acomodacionList = new ArrayList<>();
		if(idHospedaje==null) {
			Iterable<AcomodacionEntity> acomodacionEntityList = acomodacionRepository.findAll();
			for(AcomodacionEntity acomodacionEntity : acomodacionEntityList) {
				Acomodacion acomodacion = new Acomodacion();
				acomodacion.setCantidadCuartos(acomodacionEntity.getCantidad_cuartos());
				acomodacion.setDescripcion(acomodacionEntity.getDescripcion());
				acomodacion.setNombre(acomodacionEntity.getNombre());
				acomodacion.setPrecio(acomodacionEntity.getPrecio());
				acomodacion.setId(acomodacionEntity.getId());
				acomodacionList.add(acomodacion);
			}
		}
		else {
			List<AcomodacionEntity> acomodacionEntityList = acomodacionRepository.findById_hospedaje(idHospedaje);
			if(acomodacionEntityList.size()>0) {
				for(AcomodacionEntity acomodacionEntity : acomodacionEntityList) {
					Acomodacion acomodacion = new Acomodacion();
					acomodacion.setCantidadCuartos(acomodacionEntity.getCantidad_cuartos());
					acomodacion.setDescripcion(acomodacionEntity.getDescripcion());
					acomodacion.setNombre(acomodacionEntity.getNombre());
					acomodacion.setPrecio(acomodacionEntity.getPrecio());
					acomodacion.setId(acomodacionEntity.getId());
					acomodacionList.add(acomodacion);
				}
			}
			else {
				throw new HospedajeException("No hay acomodacion para el hospedaje enviado");
			}
		}
		return acomodacionList;
	}
}
