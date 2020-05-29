package com.touresbalon.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touresbalon.api.repository.read.ReadTarjetaEntity;
import com.touresbalon.api.repository.read.ReadTarjetaRepository;
import com.touresbalon.api.repository.write.WriteTarjetaEntity;
import com.touresbalon.api.repository.write.WriteTarjetaRepository;
import com.touresbalon.service.domain.ClienteException;
import com.touresbalon.service.domain.Tarjeta;

@Service
@Transactional
public class TarjetaService {

	private final ReadTarjetaRepository readRepo;
	private final WriteTarjetaRepository writeRepo;

	@Autowired
	TarjetaService(WriteTarjetaRepository writeRepo, ReadTarjetaRepository readRepo) {
		this.writeRepo = writeRepo;
		this.readRepo = readRepo;
	}

	public void createTarjetaWrite(Tarjeta tarjeta, Long idCliente) throws ClienteException {
		try {
			WriteTarjetaEntity tarjetaEntity = new WriteTarjetaEntity(tarjeta.getNumero(), tarjeta.getTipo().name(),
					tarjeta.getEstado().name(), tarjeta.getPrincipal(), idCliente);
			writeRepo.save(tarjetaEntity);
		} catch (Exception ex) {
			throw new ClienteException("Error al crear la tarjeta", ex.getCause());
		}

	}

	public void createTarjetaRead(Tarjeta tarjeta, Long idCliente) throws ClienteException {
		try {
			ReadTarjetaEntity readtarjetaEntity = new ReadTarjetaEntity(tarjeta.getNumero(), tarjeta.getTipo().name(),
					tarjeta.getEstado().name(), tarjeta.getPrincipal(), idCliente);
			readRepo.save(readtarjetaEntity);
		} catch (Exception ex) {
			throw new ClienteException("Error al crear la tarjeta", ex.getCause());
		}

	}

	public List<Tarjeta> getAllByClienteId(Long idCliente) {
		List<ReadTarjetaEntity> tarjetas = readRepo.findByIdCliente(idCliente);
		return tarjetas.stream().map(tarjetaEntity -> new Tarjeta(tarjetaEntity.getNumero(), tarjetaEntity.getTipo(),
				tarjetaEntity.getEstado())).collect(Collectors.toList());
	}

	public void updateTarjetas(List<Tarjeta> tarjetas, Long idCliente) {

		for (Tarjeta tarjeta : tarjetas) {
			WriteTarjetaEntity tarjetaEntity = new WriteTarjetaEntity(tarjeta.getNumero(), tarjeta.getTipo().name(),
					tarjeta.getEstado().name(), tarjeta.getPrincipal(), idCliente);
			writeRepo.save(tarjetaEntity);
			ReadTarjetaEntity readTarjetaEntity = readRepo.findByNumero(tarjeta.getNumero());
			if (tarjeta.getEstado() != null) {
				tarjetaEntity.setEstado(tarjeta.getEstado().name());
			}
			if (tarjeta.getPrincipal() != null) {
				tarjetaEntity.setPrincipal(tarjeta.getPrincipal());
			}
			readRepo.save(readTarjetaEntity);
		}
	}
}
