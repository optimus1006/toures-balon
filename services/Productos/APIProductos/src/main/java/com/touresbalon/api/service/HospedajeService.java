package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Ciudad;
import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.Hospedaje;
import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.domain.Imagen;
import com.touresbalon.api.domain.TipoHospedaje;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.domain.UbicacionGeografica;
import com.touresbalon.api.repository.HospedajeEntity;
import com.touresbalon.api.repository.HospedajeRepository;
import com.touresbalon.api.repository.TipoHospedajeEntity;
import com.touresbalon.api.repository.TipoHospedajeRepository;

@ApplicationScoped
@Transactional
public class HospedajeService {

	@Inject
	HospedajeRepository hospedajeRepository;
	
	@Inject
	TipoHospedajeRepository tipoHospedajeRepository;
	
	@Inject
	CuartoService cuartoService;
	
	@Inject
	ImagenService imagenService;
	
	public Hospedaje crearHospedaje(Hospedaje hospedaje) throws HospedajeException{
		Hospedaje hospedajeResponse = new Hospedaje();
		HospedajeEntity hospedajeEntity = new HospedajeEntity();
		Timestamp timestamp;
		
		if (hospedaje.getNombre() != null) {
			hospedajeEntity.setNombre(hospedaje.getNombre());
		} else {
			throw new HospedajeException("El nombre es obligatorio.");
		}
		
		if (hospedaje.getTipoHospedaje() != null) {
			TipoHospedajeEntity tipoHospedajeEntity = tipoHospedajeRepository.findByDescripcion(hospedaje.getTipoHospedaje().name()); 
			hospedajeEntity.setTipo_hospedaje(tipoHospedajeEntity.getId());
		} else {
			throw new HospedajeException("El tipo es obligatorio.");
		}
		
			hospedajeEntity.setCalificacion(hospedaje.getCalificacion());
		
		if (hospedaje.getDireccion() != null) {
			hospedajeEntity.setDireccion(hospedaje.getDireccion());
		} else {
			throw new HospedajeException("La direccion es necesariaes obligatorio.");
		}
		
		if (hospedaje.getCantidadCuartos() != null) {
			hospedajeEntity.setCantidad_cuartos(hospedaje.getCantidadCuartos());
		} else {
			throw new HospedajeException("La cantidad de cuartos es necesariaes obligatorio.");
		}
		
		if (hospedaje.getCiudad() != null) {
			hospedajeEntity.setId_ciudad(hospedaje.getCiudad().getCodigo());
		} else {
			throw new HospedajeException("La ciudad es obligatorio.");
		}
		
		if (hospedaje.getConvenio() != null) {
			hospedajeEntity.setId_convenio(hospedaje.getConvenio().getIdentificacion());
		} else {
			throw new HospedajeException("El convenio es obligatorio.");
		}
		
		if(hospedaje.getGeolocalizacion()!=null) {
			hospedajeEntity.setLatitud(hospedaje.getGeolocalizacion().getLatitud().toString());
			hospedajeEntity.setLongitud(hospedaje.getGeolocalizacion().getLongitud().toString());
		}else {
			throw new HospedajeException("La geolocalizacion es obligatoria.");
		}
		
		if(hospedaje.getInformacion()!=null) {
			hospedajeEntity.setInformacion(hospedaje.getInformacion());
		}else {
			throw new HospedajeException("La información es obligatoria.");
		}
		
		hospedajeRepository.save(hospedajeEntity);
		
		if(hospedaje.getFotos()!=null) {
			for(Imagen imagen: hospedaje.getFotos()) {
				imagen.setId(imagenService.crearImagen(imagen, hospedajeEntity.getCodigo()).getId());
			}
		}
		
		if (hospedaje.getCuartos() != null) {
			for(Cuarto cuarto: hospedaje.getCuartos()) {
				cuartoService.crearCuarto(cuarto,hospedajeEntity.getCodigo());
			}
		} else {
			throw new HospedajeException("debe definir los cuartos.");
		}
		
		hospedajeResponse.setCodigo(hospedajeEntity.getCodigo());
		hospedajeResponse.setFotos(hospedaje.getFotos());
		
		return hospedajeResponse;
	}
	
	public Hospedaje actualizar(Hospedaje hospedaje,Long codigo) throws HospedajeException{
		Hospedaje hospedajeResponse = new Hospedaje();
		Optional<HospedajeEntity> hospedajeEntity = hospedajeRepository.findById(codigo);
		
		if(hospedajeEntity.isPresent()) {
			if (hospedaje.getCalificacion() != 0) {
				hospedajeEntity.get().setCalificacion(hospedaje.getCalificacion());
			}
			if (hospedaje.getCantidadCuartos() != null) {
				hospedajeEntity.get().setCantidad_cuartos(hospedaje.getCantidadCuartos());
			}
			if (hospedaje.getNombre() != null) {
				hospedajeEntity.get().setNombre(hospedaje.getNombre());
			}
			if (hospedaje.getTipoHospedaje() != null) {
				TipoHospedajeEntity tipoHospedajeEntity = tipoHospedajeRepository.findByDescripcion(hospedaje.getTipoHospedaje().name());
				if(tipoHospedajeEntity!=null) {
					hospedajeEntity.get().setTipo_hospedaje(tipoHospedajeEntity.getId());
				}
				else {
					throw new HospedajeException("el tipo indicado no existe.");
				}
			}
			if (hospedaje.getDireccion() != null) {
				hospedajeEntity.get().setDireccion(hospedaje.getDireccion());
			}
			if (hospedaje.getCiudad() != null) {
				hospedajeEntity.get().setId_ciudad(hospedaje.getCiudad().getCodigo());
			}
			if(hospedaje.getGeolocalizacion()!=null) {
				if(hospedaje.getGeolocalizacion().getLatitud()!=null) {
					hospedajeEntity.get().setLatitud(hospedaje.getGeolocalizacion().getLatitud().toString());
				}
				if(hospedaje.getGeolocalizacion().getLongitud()!=null) {
					hospedajeEntity.get().setLongitud(hospedaje.getGeolocalizacion().getLongitud().toString());
				}
			}
			if(hospedaje.getInformacion()!=null) {
				hospedajeEntity.get().setInformacion(hospedaje.getInformacion());
			}
			hospedajeRepository.save(hospedajeEntity.get());
		}
		else {
			throw new HospedajeException("el hospedaje no existe.");
		}
		hospedajeResponse.setCodigo(hospedajeEntity.get().getCodigo());
		return hospedajeResponse;
	}
	
	public List<Hospedaje> consultarHospedajes(String idConvenio){
		List<Hospedaje> hospedajes = new ArrayList<>();
		
		if(idConvenio!=null || idConvenio.isEmpty()) {
			Iterable<HospedajeEntity> hospedajesEntity=hospedajeRepository.findAll();
			
			for(HospedajeEntity hospedajeEntity: hospedajesEntity) {
				Hospedaje hospedaje = new Hospedaje();
				hospedaje.setCalificacion(hospedajeEntity.getCalificacion());
				hospedaje.setCantidadCuartos(hospedajeEntity.getCantidad_cuartos());
				Ciudad ciudad = new Ciudad();
				ciudad.setCodigo(hospedajeEntity.getId_ciudad());
				hospedaje.setCiudad(ciudad);
				hospedaje.setCodigo(hospedajeEntity.getCodigo());
				Convenio convenio = new Convenio();
				convenio.setIdentificacion(hospedajeEntity.getId_convenio());
				hospedaje.setConvenio(convenio);
				hospedaje.setDireccion(hospedajeEntity.getDireccion());
				UbicacionGeografica geolocalizacion = new UbicacionGeografica();
				geolocalizacion.setLatitud(Float.parseFloat(hospedajeEntity.getLatitud()));
				geolocalizacion.setLongitud(Float.parseFloat(hospedajeEntity.getLongitud()));
				hospedaje.setGeolocalizacion(geolocalizacion);
				hospedaje.setInformacion(hospedajeEntity.getInformacion());
				hospedaje.setNombre(hospedajeEntity.getNombre());
				Optional<TipoHospedajeEntity> tipoHospedaje= tipoHospedajeRepository.findById(hospedajeEntity.getTipo_hospedaje());
				hospedaje.setTipoHospedaje(TipoHospedaje.valueOf(tipoHospedaje.get().getDescripcion()));
				hospedajes.add(hospedaje);
			}
		}
		else {
			List<HospedajeEntity> hospedajesEntity = hospedajeRepository.findById_convenio(idConvenio);
			
			for(HospedajeEntity hospedajeEntity: hospedajesEntity) {
				Hospedaje hospedaje = new Hospedaje();
				hospedaje.setCalificacion(hospedajeEntity.getCalificacion());
				hospedaje.setCantidadCuartos(hospedajeEntity.getCantidad_cuartos());
				Ciudad ciudad = new Ciudad();
				ciudad.setCodigo(hospedajeEntity.getId_ciudad());
				hospedaje.setCiudad(ciudad);
				hospedaje.setCodigo(hospedajeEntity.getCodigo());
				Convenio convenio = new Convenio();
				convenio.setIdentificacion(hospedajeEntity.getId_convenio());
				hospedaje.setConvenio(convenio);
				hospedaje.setDireccion(hospedajeEntity.getDireccion());
				UbicacionGeografica geolocalizacion = new UbicacionGeografica();
				geolocalizacion.setLatitud(Float.parseFloat(hospedajeEntity.getLatitud()));
				geolocalizacion.setLongitud(Float.parseFloat(hospedajeEntity.getLongitud()));
				hospedaje.setGeolocalizacion(geolocalizacion);
				hospedaje.setInformacion(hospedajeEntity.getInformacion());
				hospedaje.setNombre(hospedajeEntity.getNombre());
				Optional<TipoHospedajeEntity> tipoHospedaje= tipoHospedajeRepository.findById(hospedajeEntity.getTipo_hospedaje());
				hospedaje.setTipoHospedaje(TipoHospedaje.valueOf(tipoHospedaje.get().getDescripcion()));
				hospedajes.add(hospedaje);
			}
		
		}
		return hospedajes;
	}
}
