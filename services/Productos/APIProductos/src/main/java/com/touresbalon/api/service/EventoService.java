package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Ciudad;
import com.touresbalon.api.domain.Direccion;
import com.touresbalon.api.domain.Evento;
import com.touresbalon.api.domain.Evento.EstadoEnum;
import com.touresbalon.api.domain.Evento.TipoEnum;
import com.touresbalon.api.domain.EventoException;
import com.touresbalon.api.domain.Imagen;
import com.touresbalon.api.domain.Localidad;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.domain.UbicacionGeografica;
import com.touresbalon.api.repository.EventoEntity;
import com.touresbalon.api.repository.EventoRepository;
import com.touresbalon.api.repository.TipoEventoEntity;
import com.touresbalon.api.repository.TipoEventoRepository;

@ApplicationScoped
@Transactional
public class EventoService {

	@Inject
	private EventoRepository eventoRepository;
	
	@Inject
	private TipoEventoRepository tipoEventoRepository;
	
	@Inject
	private ImagenService imagenService;
	
	@Inject
	private LocalidadService localidadService;
	
	public Evento crearEvento(Evento evento) throws EventoException{
		Evento eventoResponse = new Evento();
		EventoEntity eventoEntity = new EventoEntity();
		Timestamp timestamp;
		
		if(evento.getCantidad()!=null) {
			eventoEntity.setCantidad(evento.getCantidad());
		}
		else {
			throw new EventoException("La cantidad de voletos del evento es obligatorio.");
		}
		
		if(evento.getDescripcion()!=null) {
			eventoEntity.setDescripcion(evento.getDescripcion());
		}
		else {
			throw new EventoException("La descripcion del evento es obligatorio.");
		}
		
		eventoEntity.setEstado(EstadoEnum.ACTIVO.name());
		
		if(evento.getFecha()!=null) {
			timestamp = Timestamp.valueOf(evento.getFecha().atStartOfDay());
			eventoEntity.setFecha(timestamp);
		}
		else {
			throw new EventoException("La fecha del evento es obligatorio.");
		}
		
		if(evento.getHora()!=null) {
			if(evento.getHora().contains(":")) {
				eventoEntity.setHora(evento.getHora());
			}
			else {
				throw new EventoException("La hora tiene un formato invalido.");
			}
		}
		else {
			throw new EventoException("La hora del evento es obligatoria.");
		}
		
		if(evento.getNombre()!=null) {
			eventoEntity.setNombre(evento.getNombre());
		}
		else {
			throw new EventoException("La nombre del evento es obligatoria.");
		}
		
		if(evento.getTipo()!=null) {
			TipoEventoEntity tipoEventoEntity = tipoEventoRepository.findByDescripcion(evento.getTipo().name());
			if(tipoEventoEntity!=null) {
				eventoEntity.setTipo(tipoEventoEntity.getId());
			}
			else {
				throw new EventoException("El tipo de evento no existe.");
			}
		}
		else {
			throw new EventoException("El tipo de evento es obligatorio.");
		}
		
		if(evento.getUbicacionEvento()!=null) {
			eventoEntity.setDireccion(evento.getUbicacionEvento().getDireccion());
			eventoEntity.setLatitud(evento.getUbicacionEvento().getUbicacion().getLatitud().toString());
			eventoEntity.setLongitud(evento.getUbicacionEvento().getUbicacion().getLongitud().toString());
			eventoEntity.setId_ciudad(evento.getUbicacionEvento().getCiudad().getCodigo());
		}
		else {
			throw new EventoException("La ubicacion del evento es obligatoria.");
		}
		
		if(evento.getZonaHoraria()!=null) {
			eventoEntity.setZona_horaria(evento.getZonaHoraria());
		}
		else {
			throw new EventoException("La zona horaria del evento es obligatoria.");
		}
		
		if (evento.getCodigoExterno() != null) {
			eventoEntity.setCodigo_externo(evento.getCodigoExterno());
		} else {
			throw new TransporteException("Debe especificar el codigo de homologacion del evento.");
		}
		
		eventoRepository.save(eventoEntity);
		eventoResponse.setId(eventoEntity.getId());
		
		if(evento.getImagenes()!=null) {
			for(Imagen imagen: evento.getImagenes()) {
				imagen.setId(imagenService.crearImagen(imagen, eventoEntity.getId(),"EVENTO").getId());
			}
			eventoResponse.setImagenes(evento.getImagenes());
		}
		
		if(evento.getLocalidades()!=null) {
			List<Localidad> listaLocalidadesResponse= new ArrayList<>();
			for(Localidad localidad: evento.getLocalidades()) {
				listaLocalidadesResponse.add(localidadService.crearLocalidad(localidad,eventoEntity.getId()));
			}
			eventoResponse.setLocalidades(listaLocalidadesResponse);
		}
		else {
			throw new EventoException("debe definir las localidades para el evento.");
		}
		
		return eventoResponse;
	}
	
	public Evento actualizar(Evento evento, Long idEvento) throws EventoException{
		
		Optional<EventoEntity> eventoEntity = eventoRepository.findById(idEvento);
		Evento eventoResponse = new Evento();
		Timestamp timestamp;
		
		if(eventoEntity.isPresent()) {
			
			eventoResponse.setId(eventoEntity.get().getId());
			
			if(evento.getCantidad()!=null) {
				eventoEntity.get().setCantidad(evento.getCantidad());
			}
			
			if(evento.getDescripcion()!=null) {
				eventoEntity.get().setDescripcion(evento.getDescripcion());
			}
			
			if(evento.getEstado()!=null) {
				eventoEntity.get().setEstado(EstadoEnum.fromValue(evento.getEstado().toString()).name());
			}
			
			
			if(evento.getFecha()!=null) {
				timestamp = Timestamp.valueOf(evento.getFecha().atStartOfDay());
				eventoEntity.get().setFecha(timestamp);
			}
			
			if(evento.getHora()!=null) {
				if(evento.getHora().contains(":")) {
					eventoEntity.get().setHora(evento.getHora());
				}
				else {
					throw new EventoException("La hora tiene un formato invalido.");
				}
			}
			
			if(evento.getNombre()!=null) {
				eventoEntity.get().setNombre(evento.getNombre());
			}
			
			if(evento.getTipo()!=null) {
				TipoEventoEntity tipoEventoEntity = tipoEventoRepository.findByDescripcion(evento.getTipo().name());
				if(tipoEventoEntity!=null) {
					eventoEntity.get().setTipo(tipoEventoEntity.getId());
				}
				else {
					throw new EventoException("El tipo de evento no existe.");
				}
			}
			
			if(evento.getUbicacionEvento()!=null) {
				eventoEntity.get().setDireccion(evento.getUbicacionEvento().getDireccion());
				eventoEntity.get().setLatitud(evento.getUbicacionEvento().getUbicacion().getLatitud().toString());
				eventoEntity.get().setLongitud(evento.getUbicacionEvento().getUbicacion().getLongitud().toString());
				eventoEntity.get().setId_ciudad(evento.getUbicacionEvento().getCiudad().getCodigo());
			}
			
			if(evento.getZonaHoraria()!=null) {
				eventoEntity.get().setZona_horaria(evento.getZonaHoraria());
			}
			
			eventoRepository.save(eventoEntity.get());
			
			if(evento.getImagenes()!=null) {
				for(Imagen imagen: evento.getImagenes()) {
					imagen.setId(imagenService.actualizar(imagen).getId());
				}
				eventoResponse.setImagenes(evento.getImagenes());
			}
			
			if(evento.getLocalidades()!=null) {
				List<Localidad> listaLocalidadesResponse= new ArrayList<>();
				for(Localidad localidad: evento.getLocalidades()) {
					listaLocalidadesResponse.add(localidadService.actualizar(localidad));
				}
				eventoResponse.setLocalidades(listaLocalidadesResponse);
			}
			
			return eventoResponse;
		}
		else {
			throw new EventoException("No existe el evento.");
		}
	}
	
	public Evento consultarPorId(Long evento) throws EventoException{
		
		Optional<EventoEntity> eventoEntity = eventoRepository.findById(evento);
		Evento eventoResponse = new Evento();
		if(eventoEntity.isPresent()) {
		
			eventoResponse.setCantidad(eventoEntity.get().getCantidad());
			eventoResponse.setDescripcion(eventoEntity.get().getDescripcion());
			eventoResponse.setEstado(EstadoEnum.fromValue(eventoEntity.get().getEstado()));
			eventoResponse.setFecha(eventoEntity.get().getFecha().toLocalDateTime().toLocalDate());
			eventoResponse.setHora(eventoEntity.get().getHora());
			eventoResponse.setId(eventoEntity.get().getId());
			eventoResponse.setImagenes(imagenService.buscarImagenesPorTipoPorId("EVENTO", eventoEntity.get().getId()));
			eventoResponse.setLocalidades(localidadService.buscarPorIdEvento(eventoEntity.get().getId()));
			eventoResponse.setNombre(eventoEntity.get().getNombre());
			eventoResponse.setTipo(TipoEnum.fromValue(tipoEventoRepository.findById(eventoEntity.get().getTipo()).toString()));
			Direccion ubicacionEvento = new Direccion();
			Ciudad ciudad = new Ciudad();
			ciudad.setCodigo(eventoEntity.get().getId_ciudad());
			ubicacionEvento.setCiudad(ciudad);
			ubicacionEvento.setDireccion(eventoEntity.get().getDireccion());
			UbicacionGeografica ubicacion = new UbicacionGeografica();
			ubicacion.setLatitud(Float.parseFloat(eventoEntity.get().getLatitud()));
			ubicacion.setLongitud(Float.parseFloat(eventoEntity.get().getLongitud()));
			ubicacionEvento.setUbicacion(ubicacion);
			eventoResponse.setUbicacionEvento(ubicacionEvento);
			
			return eventoResponse;
		}
		else {
			throw new EventoException("No existe el evento.");
		}
	}
	
	public List<Evento> listarEventos() throws EventoException{
		Iterable<EventoEntity> eventosEntity = eventoRepository.findAll();
		List<Evento> eventos = new ArrayList<>();
		
		for(EventoEntity eventoEntity : eventosEntity) {
			Evento evento = new Evento();
			evento.setCantidad(eventoEntity.getCantidad());
			evento.setDescripcion(eventoEntity.getDescripcion());
			evento.setEstado(EstadoEnum.fromValue(eventoEntity.getEstado()));
			evento.setFecha(eventoEntity.getFecha().toLocalDateTime().toLocalDate());
			evento.setHora(eventoEntity.getHora());
			evento.setId(eventoEntity.getId());
			evento.setImagenes(imagenService.buscarImagenesPorTipoPorId("EVENTO", eventoEntity.getId()));
			evento.setLocalidades(localidadService.buscarPorIdEvento(eventoEntity.getId()));
			evento.setNombre(eventoEntity.getNombre());
			evento.setTipo(TipoEnum.fromValue(tipoEventoRepository.findById(eventoEntity.getTipo()).toString()));
			Direccion ubicacionEvento = new Direccion();
			Ciudad ciudad = new Ciudad();
			ciudad.setCodigo(eventoEntity.getId_ciudad());
			ubicacionEvento.setCiudad(ciudad);
			ubicacionEvento.setDireccion(eventoEntity.getDireccion());
			UbicacionGeografica ubicacion = new UbicacionGeografica();
			ubicacion.setLatitud(Float.parseFloat(eventoEntity.getLatitud()));
			ubicacion.setLongitud(Float.parseFloat(eventoEntity.getLongitud()));
			ubicacionEvento.setUbicacion(ubicacion);
			evento.setUbicacionEvento(ubicacionEvento);
			eventos.add(evento);
		}
		return eventos;
	}
}
