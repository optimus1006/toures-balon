package com.touresbalon.api.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.DireccionEntity;
import com.touresbalon.api.repository.DireccionRepository;
import com.touresbalon.service.domain.Ciudad;
import com.touresbalon.service.domain.Direccion;
import com.touresbalon.service.domain.Direccion.TipoEnum;
import com.touresbalon.service.domain.Estado;
import com.touresbalon.service.domain.Pais;
import com.touresbalon.service.domain.UbicacionGeografica;

@Service
@Transactional
public class DireccionService {
	@Autowired
    private DireccionRepository direccionRepository;
	
	public void crearDireccion(Direccion direccion,Long idCliente) {
		Date fechaCreacion = new Date();
		DireccionEntity direccionEntity = new DireccionEntity(
				direccion.getDireccion(),
				direccion.getTipo().name(),
				direccion.getPais().getCodigo(),
				direccion.getCiudad().getCodigo(),
				direccion.getEstado().getCodigo(),
				fechaCreacion,
				idCliente,
				direccion.getUbicacion().getLatitud(),
				direccion.getUbicacion().getLongitud());
		direccionRepository.save(direccionEntity);
	}
	
	public List<Direccion> getAllDireccionesByClienteId(Long clienteId){
		List<DireccionEntity> direcciones = direccionRepository.findAllByIdCliente(clienteId);
		List<Direccion> direccionesResponse = new ArrayList<>();
		for(DireccionEntity direccion: direcciones) {
			Direccion direccionResponse = new Direccion();
			direccionResponse.setDireccion(direccion.getDireccion());
			direccionResponse.setCodigo(direccion.getCodigo());
			Ciudad ciudad =  new Ciudad();
			ciudad.setCodigo(direccion.getIdCiudad());
			Pais pais = new Pais();
			pais.setCodigo(direccion.getIdPais());
			ciudad.setPais(pais);
			direccionResponse.setCiudad(ciudad);
			Estado estado = new Estado();
			estado.setCodigo(direccion.getIdEstado());
			direccionResponse.setEstado(estado);
			int hour = 0;
			int minute = 0;
			OffsetDateTime offsetDateTime = direccion.getFechaCreacion().toInstant()
			  .atOffset(ZoneOffset.ofHoursMinutes(hour, minute));
			direccionResponse.setFechaCreacion(offsetDateTime);
			direccionResponse.setTipo(TipoEnum.valueOf(direccion.getTipo()));
			UbicacionGeografica ubicacionGeografica = new UbicacionGeografica();
			ubicacionGeografica.setLatitud(Float.parseFloat(direccion.getLatitud()));
			ubicacionGeografica.setLongitud(Float.parseFloat(direccion.getLongitud()));
			direccionResponse.setUbicacion(ubicacionGeografica);
			direccionesResponse.add(direccionResponse);
		}
		return direccionesResponse;
	}
	
	public void updateDirecciones(List<Direccion> direcciones) {
		for(Direccion direccion: direcciones) {
			DireccionEntity direccionEntity = direccionRepository.findByCodigo(direccion.getCodigo());
			if(direccion.getCiudad()!=null) {
				direccionEntity.setIdCiudad(direccion.getCiudad().getCodigo());
				if(direccion.getCiudad().getPais()!=null) {
					direccionEntity.setIdPais(direccion.getCiudad().getPais().getCodigo());
				}
			}
			if(direccion.getDireccion()!=null) {
				direccionEntity.setDireccion(direccion.getDireccion());
			}
			if(direccion.getEstado()!=null) {
				direccionEntity.setIdEstado(direccion.getEstado().getCodigo());
			}
			if(direccion.getTipo()!=null) {
				direccionEntity.setTipo(direccion.getTipo().name());
			}
			if(direccion.getUbicacion()!=null) {
				if(direccion.getUbicacion().getLatitud()!=null){
					direccionEntity.setLatitud(direccion.getUbicacion().getLatitud().toString());
				}
				if(direccion.getUbicacion().getLongitud()!=null) {
					direccionEntity.setLongitud(direccion.getUbicacion().getLongitud().toString());
				}
			}
			direccionRepository.save(direccionEntity);
		}
	}
	
	public void deleteDireccionesByIdCliente(Long idCliente) {
		direccionRepository.deleteByIdCliente(idCliente);
	}
}
