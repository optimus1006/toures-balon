package com.touresbalon.api.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.read.ReadDireccionEntity;
import com.touresbalon.api.repository.read.ReadDireccionRepository;
import com.touresbalon.api.repository.write.WriteDireccionEntity;
import com.touresbalon.api.repository.write.WriteDireccionRepository;
import com.touresbalon.service.domain.Ciudad;
import com.touresbalon.service.domain.ClienteException;
import com.touresbalon.service.domain.Direccion;
import com.touresbalon.service.domain.Direccion.TipoEnum;
import com.touresbalon.service.domain.Estado;
import com.touresbalon.service.domain.Pais;
import com.touresbalon.service.domain.UbicacionGeografica;

@Service
@Transactional
public class DireccionService {

	private final ReadDireccionRepository readRepo;
	private final WriteDireccionRepository writeRepo;

	@Autowired
	DireccionService(WriteDireccionRepository writeRepo, ReadDireccionRepository readRepo) {
		this.writeRepo = writeRepo;
		this.readRepo = readRepo;
	}

	public void crearDireccionWrite(Direccion direccion, Long idCliente) {
		try {
			Date fechaCreacion = new Date();
			WriteDireccionEntity direccionEntity = new WriteDireccionEntity(direccion.getDireccion(),
					direccion.getTipo().name(), direccion.getPais().getCodigo(), direccion.getCiudad().getCodigo(),
					direccion.getEstado().getCodigo(), fechaCreacion, idCliente, direccion.getUbicacion().getLatitud(),
					direccion.getUbicacion().getLongitud());
			writeRepo.save(direccionEntity);
		} catch (Exception ex) {
			throw new ClienteException("Error al crear la direccion", ex.getCause());
		}
	}

	public void crearDireccionRead(Direccion direccion, Long idCliente) {
		try {
			Date fechaCreacion = new Date();

			ReadDireccionEntity readdireccionEntity = new ReadDireccionEntity(direccion.getDireccion(),
					direccion.getTipo().name(), direccion.getPais().getCodigo(), direccion.getCiudad().getCodigo(),
					direccion.getEstado().getCodigo(), fechaCreacion, idCliente, direccion.getUbicacion().getLatitud(),
					direccion.getUbicacion().getLongitud());
			readRepo.save(readdireccionEntity);
		} catch (Exception ex) {
			throw new ClienteException("Error al crear la direccion", ex.getCause());
		}
	}
	
	public List<Direccion> getAllDireccionesByClienteId(Long clienteId){
		List<ReadDireccionEntity> direcciones = readRepo.findAllByIdCliente(clienteId);
		List<Direccion> direccionesResponse = new ArrayList<>();
		for(ReadDireccionEntity direccion: direcciones) {
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
			LocalDateTime offsetDateTime = direccion.getFechaCreacion().toInstant().
					atZone(ZoneId.systemDefault()).toLocalDateTime();
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
	
	public void updateDirecciones(List<Direccion> direcciones, Long idCliente) {
		Date fechaCreacion = new Date();
		for(Direccion direccion: direcciones) {
			WriteDireccionEntity writeDireccionEntity = new WriteDireccionEntity(direccion.getDireccion(),
					direccion.getTipo().name(), direccion.getPais().getCodigo(), direccion.getCiudad().getCodigo(),
					direccion.getEstado().getCodigo(), fechaCreacion, idCliente, direccion.getUbicacion().getLatitud(),
					direccion.getUbicacion().getLongitud());
			writeRepo.save(writeDireccionEntity);
			ReadDireccionEntity direccionEntity = readRepo.findByCodigo(direccion.getCodigo());
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
			readRepo.save(direccionEntity);
		}
	}
}
