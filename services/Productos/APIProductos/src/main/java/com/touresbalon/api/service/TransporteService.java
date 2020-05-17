package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.Ciudad;
import com.touresbalon.api.domain.Convenio;
import com.touresbalon.api.domain.Transporte;
import com.touresbalon.api.domain.Transporte.EstadoEnum;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.domain.Vehiculo;
import com.touresbalon.api.repository.TransporteEntity;
import com.touresbalon.api.repository.TransporteRepository;

@ApplicationScoped
@Transactional
public class TransporteService {

	@Inject
	TransporteRepository transporteRepository;

	@Inject
	VehiculoService vehiculoService;
	
	@Inject
	AsientoService asientoService;

	public Transporte crearTransporte(Transporte transporte) throws TransporteException {
		Transporte transporteResponse = new Transporte();
		TransporteEntity transporteEntity = new TransporteEntity();
		Timestamp timestamp;

		if (transporte.getVehiculo() != null) {
			Vehiculo vehiculo = vehiculoService.buscarPorId(transporte.getVehiculo().getId());
			if (vehiculo != null) {
				transporteEntity.setId_vehiculo(vehiculo.getId());
			} else {
				throw new TransporteException("El vehiculo indicado no existe");
			}
		}
		if (transporte.getDescription() != null) {
			transporteEntity.setDescripcion(transporte.getDescription());
		} else {
			throw new TransporteException("La descripcion es obligatoria");
		}

		if (transporte.getFechaPartida() != null) {
			timestamp = Timestamp.valueOf(transporte.getFechaPartida());
			transporteEntity.setFecha_partida(timestamp);
		} else {
			throw new TransporteException("La fecha de partida es obligatoria");
		}

		if (transporte.getLugarPartida() != null) {
			transporteEntity.setLugar_partida(transporte.getLugarPartida().getCodigo());
		} else {
			throw new TransporteException("El lugar de partida es obligatorio");
		}

		if (transporte.getFechaLlegada() != null) {
			timestamp = Timestamp.valueOf(transporte.getFechaLlegada());
			transporteEntity.setFecha_llegada(timestamp);
		} else {
			throw new TransporteException("La fecha de llegada es obligatorio");
		}

		if (transporte.getLugarDestino() != null) {
			transporteEntity.setLugar_llegada(transporte.getLugarDestino().getCodigo());
		} else {
			throw new TransporteException("El lugar de destino es obligatorio");
		}

		if (transporte.getConvenio() != null) {
			transporteEntity.setConvenio(transporte.getConvenio().getIdentificacion());
		} else {
			throw new TransporteException("El convenio es obligatorio");
		}

		if (transporte.getCantidadCupos() != null) {
			transporteEntity.setCantidad_cupos(transporte.getCantidadCupos());
		} else {
			throw new TransporteException("Debe especificar la cantidad de cupos disponibles");
		}

		if (transporte.getTipo() != null) {
			transporteEntity.setTipo(transporte.getTipo().name());
		} else {
			throw new TransporteException("Debe especificar el tipo del transporte");
		}
		
		if (transporte.getValor() != null) {
			transporteEntity.setValor(transporte.getValor().longValue());
		} else {
			throw new TransporteException("Debe especificar el valor del transporte");
		}

		transporteEntity.setEstado(EstadoEnum.ACTIVO.name());

		transporteEntity.setFecha_creacion(new Timestamp(System.currentTimeMillis()));

		transporteRepository.save(transporteEntity);
		Long id = transporteEntity.getId();
		
		if(transporte.getAsientos()!=null && transporte.getAsientos().size()>0) {
			for(Asiento asiento: transporte.getAsientos()) {
				Long idAsiento=asientoService.crearAsiento(asiento, id);
				asiento.setId(idAsiento);
			}
			transporteResponse.setAsientos(transporte.getAsientos());
		}
		else {
			throw new TransporteException("Los asientos del transporte son obligatorios");
		}
		transporteResponse.setId(id);
		
		return transporteResponse;
	}

	public List<Transporte> listarTransportes() {
		Iterable<TransporteEntity> transportesEntity = transporteRepository.findAll();
		List<Transporte> transportesReturn = new ArrayList<>();
		Transporte transporte;
		for (TransporteEntity transporteEntity : transportesEntity) {
			transporte = new Transporte();
			transporte.setId(transporteEntity.getId());
			transporte.setDescription(transporteEntity.getDescripcion());
			transporte.setCantidadCupos(transporteEntity.getCantidad_cupos());
			Convenio convenio = new Convenio();
			convenio.setIdentificacion(transporteEntity.getConvenio());
			transporte.setConvenio(convenio);
			transporte.setEstado(EstadoEnum.valueOf(transporteEntity.getEstado()));
			transporte.setFechaLlegada(transporteEntity.getFecha_llegada().toLocalDateTime());
			Ciudad ciudad = new Ciudad();
			ciudad.setCodigo(transporteEntity.getLugar_llegada());
			transporte.setLugarDestino(ciudad);
			transporte.setFechaPartida(transporteEntity.getFecha_partida().toLocalDateTime());
			ciudad.setCodigo(transporteEntity.getLugar_partida());
			transporte.setLugarPartida(ciudad);
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setId(transporteEntity.getId_vehiculo());
			transporte.setVehiculo(vehiculo);
			transportesReturn.add(transporte);
		}
		return transportesReturn;
	}

	public Transporte getTransportePorId(Long id) throws TransporteException {
		Optional<TransporteEntity> transporteEntity = transporteRepository.findById(id);
		Transporte transporte = new Transporte();
		if (transporteEntity.isPresent()) {
			transporte.setCantidadCupos(transporteEntity.get().getCantidad_cupos());
			Convenio convenio = new Convenio();
			convenio.setIdentificacion(transporteEntity.get().getConvenio());
			transporte.setConvenio(convenio);
			transporte.setDescription(transporteEntity.get().getDescripcion());
			transporte.setEstado(EstadoEnum.valueOf(transporteEntity.get().getEstado()));
			transporte.setFechaLlegada(transporteEntity.get().getFecha_llegada().toLocalDateTime());
			transporte.setFechaPartida(transporteEntity.get().getFecha_partida().toLocalDateTime());
			transporte.setId(transporteEntity.get().getId());
			Ciudad ciudad = new Ciudad();
			ciudad.setCodigo(transporteEntity.get().getLugar_llegada());
			transporte.setLugarDestino(ciudad);
			ciudad.setCodigo(transporteEntity.get().getLugar_partida());
			transporte.setLugarPartida(ciudad);
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setId(transporteEntity.get().getId_vehiculo());
			transporte.setVehiculo(vehiculo);
		} else {
			throw new TransporteException("No hay ningun transporte asociado a ese id.");
		}
		return transporte;
	}

	public Long actualizarTransporte(Transporte transporte, Long id) {
//		Optional<TransporteEntity> transporteEntity = transporteRepository.findById(id);
//		if(transporteEntity.isPresent()) {
//			if(transporte.getCantidadCupos()!=null) {
//				transporteEntity.get().setCantidad_cupos(transporte.getCantidadCupos());
//			}
//			if(transporte.getConvenio()!=null) {
//				transporteEntity.get().setConvenio(transporte.getConvenio().getIdentificacion());
//			}
//			if(transporte.getDescription()!=null) {
//				transporteEntity.get().setDescripcion(transporte.getDescription());
//			}
//			if(transporte.getFechaLlegada()!=null) {
//				transporteEntity.get().setFecha_llegada(fecha_llegada);
//			}
//		}
		try {
			TransporteEntity transporteEntity = new TransporteEntity();
			Timestamp timestamp;

			if (transporte.getVehiculo() != null) {
				Vehiculo vehiculo = vehiculoService.buscarPorId(transporte.getVehiculo().getId());
				if (vehiculo != null) {
					transporteEntity.setId_vehiculo(vehiculo.getId());
				} else {
					throw new TransporteException("El vehiculo indicado no existe");
				}
			}
			if (transporte.getDescription() != null) {
				transporteEntity.setDescripcion(transporte.getDescription());
			}
			if (transporte.getFechaPartida() != null) {
				timestamp = Timestamp.valueOf(transporte.getFechaPartida());
				transporteEntity.setFecha_partida(timestamp);
			}

			if (transporte.getLugarPartida() != null) {
				transporteEntity.setLugar_partida(transporte.getLugarPartida().getCodigo());
			}

			if (transporte.getFechaLlegada() != null) {
				timestamp = Timestamp.valueOf(transporte.getFechaLlegada());
				transporteEntity.setFecha_llegada(timestamp);
			}

			if (transporte.getLugarDestino() != null) {
				transporteEntity.setLugar_llegada(transporte.getLugarDestino().getCodigo());
			}

			if (transporte.getConvenio() != null) {
				transporteEntity.setConvenio(transporte.getConvenio().getIdentificacion());
			}

			if (transporte.getCantidadCupos() != null) {
				transporteEntity.setCantidad_cupos(transporte.getCantidadCupos());
			}

			if (transporte.getEstado() != null) {
				transporteEntity.setEstado(transporte.getEstado().name());
			}

			transporteEntity.setFecha_creacion(new Timestamp(System.currentTimeMillis()));

			transporteRepository.save(transporteEntity);
			Long idResponse = transporteEntity.getId();
			return idResponse;
		} catch (TransporteException e) {
			throw new TransporteException("Error en la creacion del transporte", e.getCause());
		}
	}

	public void borrarTransporte(Long id) {
//		TransporteEntity transporteEntity = new TransporteEntity();
//		transporteEntity.setId(id);
//		transporteEntity
	}
}
