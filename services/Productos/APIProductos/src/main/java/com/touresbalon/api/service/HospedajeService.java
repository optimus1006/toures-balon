package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Acomodacion;
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
	
	@Inject
	AcomodacionService acomodacionService;
	
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
		
		if (hospedaje.getCodigoExterno() != null) {
			hospedajeEntity.setCodigo_externo(hospedaje.getCodigoExterno());
		} else {
			throw new TransporteException("Debe especificar el codigo de homologacion del hospedaje.");
		}
		
		hospedajeRepository.save(hospedajeEntity);
		
		if(hospedaje.getFotos()!=null) {
			for(Imagen imagen: hospedaje.getFotos()) {
				imagen.setId(imagenService.crearImagen(imagen, hospedajeEntity.getCodigo(),"HOSPEDAJE").getId());
			}
		}
		
		if (hospedaje.getAcomodaciones() != null) {
			for(Acomodacion acomodacion: hospedaje.getAcomodaciones()) {
				acomodacion.setId(acomodacionService.crearAcomodacion(acomodacion).getId());
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
	
	public List<Hospedaje> consultarHospedajes(String nombre, int calificacion, String tipoHospedaje,Long ciudad,String idConvenio){
		List<Hospedaje> hospedajes = new ArrayList<>();
		List<HospedajeEntity> hospedajesEntityA = new ArrayList<>();
		
		if(idConvenio!=null) {
			hospedajesEntityA = hospedajeRepository.findById_convenio(idConvenio);
		}
		else if(nombre!=null) {
			hospedajesEntityA = hospedajeRepository.findByNombre(nombre);
		}
		else if(tipoHospedaje!=null) {
			TipoHospedajeEntity tipoHospedajeEntity=tipoHospedajeRepository.findByDescripcion(tipoHospedaje);
			if(tipoHospedajeEntity!=null) {
				hospedajesEntityA = hospedajeRepository.findByTipo_hospedaje(tipoHospedajeEntity.getId());
			}
			else {
				throw new HospedajeException("el tipo de hospedaje no existe.");
			}
		}
		else if(ciudad!=null) {
			hospedajesEntityA = hospedajeRepository.findById_ciudad(ciudad);
		}
		else if(calificacion!=0) {
			hospedajesEntityA = hospedajeRepository.findByCalificacion(calificacion);
		}
		else {
			Iterable<HospedajeEntity> hospedajesEntityI=hospedajeRepository.findAll();
			for(HospedajeEntity hospedajeEntity: hospedajesEntityI) {
				Hospedaje hospedaje = new Hospedaje();
				hospedaje.setCalificacion(hospedajeEntity.getCalificacion());
				hospedaje.setCantidadCuartos(hospedajeEntity.getCantidad_cuartos());
				Ciudad ciudadR = new Ciudad();
				ciudadR.setCodigo(hospedajeEntity.getId_ciudad());
				hospedaje.setCiudad(ciudadR);
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
				Optional<TipoHospedajeEntity> tipoHospedajeO= tipoHospedajeRepository.findById(hospedajeEntity.getTipo_hospedaje());
				hospedaje.setTipoHospedaje(TipoHospedaje.valueOf(tipoHospedajeO.get().getDescripcion()));
				hospedajes.add(hospedaje);
			}
		}	
		
		if(hospedajesEntityA.size()>0) {
			for(HospedajeEntity hospedajeEntity: hospedajesEntityA) {
				Hospedaje hospedaje = new Hospedaje();
				hospedaje.setCalificacion(hospedajeEntity.getCalificacion());
				hospedaje.setCantidadCuartos(hospedajeEntity.getCantidad_cuartos());
				Ciudad ciudadR = new Ciudad();
				ciudadR.setCodigo(hospedajeEntity.getId_ciudad());
				hospedaje.setCiudad(ciudadR);
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
				Optional<TipoHospedajeEntity> tipoHospedajeO= tipoHospedajeRepository.findById(hospedajeEntity.getTipo_hospedaje());
				hospedaje.setTipoHospedaje(TipoHospedaje.valueOf(tipoHospedajeO.get().getDescripcion()));
				hospedajes.add(hospedaje);
			}
		}	
			
			
		return hospedajes;
	}
	
	public Hospedaje consultarPorId(Long id) {
		Hospedaje hospedaje = new Hospedaje();
		Optional<HospedajeEntity> hospedajeEntity = hospedajeRepository.findById(id);
		hospedaje.setCalificacion(hospedajeEntity.get().getCalificacion());
		hospedaje.setCantidadCuartos(hospedajeEntity.get().getCantidad_cuartos());
		Ciudad ciudad = new Ciudad();
		ciudad.setCodigo(hospedajeEntity.get().getId_ciudad());
		hospedaje.setCiudad(ciudad);
		hospedaje.setCodigo(hospedajeEntity.get().getCodigo());
		Convenio convenio = new Convenio();
		convenio.setIdentificacion(hospedajeEntity.get().getId_convenio());
		hospedaje.setConvenio(convenio);
		hospedaje.setDireccion(hospedajeEntity.get().getDireccion());
		UbicacionGeografica geolocalizacion = new UbicacionGeografica();
		geolocalizacion.setLatitud(Float.parseFloat(hospedajeEntity.get().getLatitud()));
		geolocalizacion.setLongitud(Float.parseFloat(hospedajeEntity.get().getLongitud()));
		hospedaje.setGeolocalizacion(geolocalizacion);
		hospedaje.setInformacion(hospedajeEntity.get().getInformacion());
		hospedaje.setNombre(hospedajeEntity.get().getNombre());
		Optional<TipoHospedajeEntity> tipoHospedaje= tipoHospedajeRepository.findById(hospedajeEntity.get().getTipo_hospedaje());
		hospedaje.setTipoHospedaje(TipoHospedaje.valueOf(tipoHospedaje.get().getDescripcion()));
		
		return hospedaje;
	}
}
