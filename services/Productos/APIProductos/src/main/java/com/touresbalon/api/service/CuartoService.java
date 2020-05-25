package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Cliente;
import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.HospedajeException;
import com.touresbalon.api.repository.CuartoEntity;
import com.touresbalon.api.repository.CuartoRepository;

@ApplicationScoped
@Transactional
public class CuartoService {

	@Inject
	CuartoRepository cuartoRepository;
	
	public Cuarto crearCuarto(Cuarto cuarto, Long acomodacion) throws HospedajeException{
		CuartoEntity cuartoEntity = new CuartoEntity();
		
		if(cuarto.getNumeroCuarto()!=null) {
			cuartoEntity.setNumero_cuarto(cuarto.getNumeroCuarto());
		}
		else {
			throw new HospedajeException("el numero del cuarto es obligatorio.");
		}
		
		cuartoEntity.setId_acomodacion(acomodacion);
		
		if(cuarto.getCliente()!=null) {
			cuartoEntity.setId_cliente(cuarto.getCliente().getId());
			Timestamp timestamp;
			if(cuarto.getFechaReservaInicio()!=null) {
				timestamp = Timestamp.valueOf(cuarto.getFechaReservaInicio());
				cuartoEntity.setFecha_inicio(timestamp);
			}
			else {
				throw new HospedajeException("debe especificar la fecha inicial de la reserva");
			}
			
			if(cuarto.getFechaReservaFin()!=null) {
				timestamp = Timestamp.valueOf(cuarto.getFechaReservaFin());
				cuartoEntity.setFecha_fin(timestamp);
			}
			else {
				throw new HospedajeException("debe especificar la fecha final de la reserva");
			}
			cuartoEntity.setFecha_reserva(new Timestamp(System.currentTimeMillis()));
		}
		else {
			cuartoEntity.setId_cliente(0L);
		}
		
		cuartoRepository.save(cuartoEntity);
		
		Cuarto cuartoResponse = new Cuarto();
		cuartoResponse.setId(cuartoEntity.getId());
		
		return cuartoResponse;
	}
	
	public Cuarto actualizarCuarto(Cuarto cuarto,Long cuartoId,boolean reserva) throws HospedajeException{
		Optional<CuartoEntity> cuartoEntity = cuartoRepository.findById(cuartoId);
		Timestamp timestamp;
		if(cuartoEntity.isPresent()) {
			if (reserva) {
				if(cuarto.getFechaReservaInicio()!=null) {
					timestamp = Timestamp.valueOf(cuarto.getFechaReservaInicio());
					cuartoEntity.get().setFecha_inicio(timestamp);
				}
				else {
					throw new HospedajeException("debe especificar la fecha inicial de la reserva");
				}
				
				if(cuarto.getFechaReservaFin()!=null) {
					timestamp = Timestamp.valueOf(cuarto.getFechaReservaFin());
					cuartoEntity.get().setFecha_fin(timestamp);
				}
				else {
					throw new HospedajeException("debe especificar la fecha final de la reserva");
				}
				if(cuarto.getCliente()!=null) {
					cuartoEntity.get().setId_cliente(cuarto.getId());
				}
				else {
					throw new HospedajeException("debe especificar el id del cliente");
				}
				cuartoEntity.get().setFecha_reserva(new Timestamp(System.currentTimeMillis()));
				cuartoRepository.save(cuartoEntity.get());
			}
			else {
				cuartoEntity.get().setFecha_inicio(null);
				cuartoEntity.get().setFecha_fin(null);
				cuartoEntity.get().setId_cliente(0L);
				cuartoEntity.get().setFecha_reserva(null);
			}
			
		}
		else {
			throw new HospedajeException("No existe el cuarto con el id indicado");
		}
		Cuarto cuartoResponse = new Cuarto();
		cuartoResponse.setId(cuartoId);
		return cuartoResponse;
	}
	
	public List<Cuarto> listarCuartos(Long idHospedaje,Long idCliente) throws HospedajeException{
		List<Cuarto> cuartos = new ArrayList<>();
		List<CuartoEntity> cuartosEntity = new ArrayList<CuartoEntity>();
		if(idCliente!=null) {
			cuartosEntity=cuartoRepository.findById_hospedaje(idHospedaje);
		}
		else {
			cuartosEntity=cuartoRepository.findById_hospedajeAndId_cliente(idHospedaje, idCliente);
		}
		
		if(cuartosEntity.size()>0) {
			for(CuartoEntity cuarto: cuartosEntity) {
				Cuarto cuartoR = new Cuarto();
				cuartoR.setFechaReserva(cuarto.getFecha_reserva().toLocalDateTime());
				cuartoR.setFechaReservaFin(cuarto.getFecha_fin().toLocalDateTime());
				cuartoR.setFechaReservaInicio(cuarto.getFecha_inicio().toLocalDateTime());
				cuartoR.setId(cuarto.getId());
				Cliente cliente = new  Cliente();
				cliente.setId(cuarto.getId_cliente());
				cuartoR.setCliente(cliente);
				cuartoR.setNumeroCuarto(cuarto.getNumero_cuarto());
				cuartos.add(cuartoR);
			}
			return cuartos;
		}
		else {
			throw new HospedajeException("No existen cuartos para este hospedaje");
		}
	}
	
	public Cuarto listarCuarto(Long id) throws HospedajeException{
		Cuarto cuartoR = new Cuarto();
		Optional<CuartoEntity> cuarto = cuartoRepository.findById(id);
		
		if(cuarto.isPresent()) {
			cuartoR.setFechaReserva(cuarto.get().getFecha_reserva().toLocalDateTime());
			cuartoR.setFechaReservaFin(cuarto.get().getFecha_fin().toLocalDateTime());
			cuartoR.setFechaReservaInicio(cuarto.get().getFecha_inicio().toLocalDateTime());
			cuartoR.setId(cuarto.get().getId());
			Cliente cliente = new  Cliente();
			cliente.setId(cuarto.get().getId_cliente());
			cuartoR.setCliente(cliente);
			cuartoR.setNumeroCuarto(cuarto.get().getNumero_cuarto());
			
			return cuartoR;
		}
		else {
			throw new HospedajeException("No existe este cuarto");
		}
	}
}
