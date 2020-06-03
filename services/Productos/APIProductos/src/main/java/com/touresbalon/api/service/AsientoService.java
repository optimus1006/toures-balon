package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.Cliente;
import com.touresbalon.api.domain.EventoException;
import com.touresbalon.api.domain.TransporteException;
import com.touresbalon.api.repository.AsientoEntity;
import com.touresbalon.api.repository.AsientoEventoEntity;
import com.touresbalon.api.repository.AsientoEventoRepository;
import com.touresbalon.api.repository.AsientoRepository;

@ApplicationScoped
@Transactional
public class AsientoService {
	
	@Inject
	AsientoRepository asientoRepository;
	
	@Inject
	AsientoEventoRepository asientoEventoRepository;
	
	public Long crearAsiento(Asiento asiento,Long idTransporte,Long idProducto) throws TransporteException{
		AsientoEntity asientoEntity = new AsientoEntity();
		asientoEntity.setId_transporte(idTransporte);
		if(asiento.getIdCliente()!=null) {
			asientoEntity.setId_cliente(asiento.getIdCliente().getId());
			asientoEntity.setId_producto(idProducto);
		}
		
		if(asiento.getValor()!=null) {
			asientoEntity.setValor(asiento.getValor());
		}
//		else {
//			throw new TransporteException("El codigo de silleteria del asiento es obligatorio");
//		}
		
		asientoEntity.setFecha_reserva(new Timestamp(System.currentTimeMillis()));
		
		asientoRepository.save(asientoEntity);
		
		return asientoEntity.getId();
		
	}
	
	public Asiento actualizarAsiento(Long idTransporte,Asiento asiento,Long idAsiento) throws TransporteException{
		AsientoEntity asientoEntity = asientoRepository.findAsientoByIdAndId_transporte(idAsiento,idTransporte);
		Asiento asientoResponse = new Asiento();
		if(asientoEntity!=null) {
			if(asiento.getValor()!=null) {
				asientoEntity.setValor(asiento.getValor());
			}
			if(asiento.getIdCliente()!=null) {
				asientoEntity.setId_cliente(asiento.getIdCliente().getId());
				asientoEntity.setFecha_reserva(new Timestamp(System.currentTimeMillis()));
			}
			
			asientoRepository.save(asientoEntity);
			asientoResponse.setId(asientoEntity.getId());
		}
		else {
			throw new TransporteException("Verifique el id del asiento o el id del transporte");
		}
		return asientoResponse;
	}
	
	public List<Asiento> buscarPorTransporte(Long idTransporte,Long idCliente, Long idProducto) throws TransporteException{
		List<AsientoEntity> asientos = new ArrayList<>();
		
		if(idCliente!=null){
			asientos = asientoRepository.findAsientoById_transporteAndId_cliente(idTransporte, idCliente);
		}
		else if(idProducto!=null && idTransporte!=null) {
			asientos = asientoRepository.findById_transporteAndId_producto(idTransporte, idProducto);
		}
		else if(idProducto!=null && idTransporte==null) {
			asientos = asientoRepository.findById_producto(idProducto);
		}
		else {
			asientos = asientoRepository.findAsientoById_transporte(idTransporte);
		}
		 
		List<Asiento> asientosResponse = new ArrayList<>();
		if(!asientos.isEmpty()) {
			for(AsientoEntity asientoEntity: asientos) {
				Asiento asiento = new Asiento();
				asiento.setId(asientoEntity.getId());
				Cliente cliente =  new Cliente();
				cliente.setId(asientoEntity.getId_cliente());
				asiento.setIdCliente(cliente);
				asiento.setValor(asientoEntity.getValor());
				if(asientoEntity.getFecha_reserva()!=null) {
					asiento.setFechaReserva(asientoEntity.getFecha_reserva().toLocalDateTime());
				}
				else {
					asiento.setFechaReserva(null);
				}
				
				asientosResponse.add(asiento);
			}
		}
		
		return asientosResponse;
	}
	
	public List<Asiento> buscarPorEvento(Long idLocalidad,Long idCliente, Long idProducto) throws TransporteException{
		List<AsientoEventoEntity> asientos = new ArrayList<>();
		
		if(idCliente!=null){
			asientos = asientoEventoRepository.findAsientoById_localidadAndId_cliente(idLocalidad, idCliente);
		}
		else if(idProducto!=null && idLocalidad!=null) {
			asientos = asientoEventoRepository.findById_localidadAndId_producto(idLocalidad, idProducto);
		}
		else if(idProducto!=null && idLocalidad==null) {
			asientos = asientoEventoRepository.findById_producto(idProducto);
		}
		else {
			asientos = asientoEventoRepository.findAsientoById_localidad(idLocalidad);
		}
		 
		List<Asiento> asientosResponse = new ArrayList<>();
		if(!asientos.isEmpty()) {
			for(AsientoEventoEntity asientoEntity: asientos) {
				Asiento asiento = new Asiento();
				asiento.setId(asientoEntity.getId());
				Cliente cliente =  new Cliente();
				cliente.setId(asientoEntity.getId_cliente());
				asiento.setIdCliente(cliente);
				asiento.setValor(asientoEntity.getNumero());
				if(asientoEntity.getFecha_reserva()!=null) {
					asiento.setFechaReserva(asientoEntity.getFecha_reserva().toLocalDateTime());
				}
				else {
					asiento.setFechaReserva(null);
				}
				
				asientosResponse.add(asiento);
			}
		}
		
		return asientosResponse;
	}
	
	public Asiento buscarAsientoPorid(Long idAsiento) throws TransporteException{
		Optional<AsientoEntity> asientoEntity = asientoRepository.findById(idAsiento);
		Asiento asiento = new Asiento();
		if(asientoEntity.isPresent()) {
			asiento.setId(asientoEntity.get().getId());
			Cliente cliente =  new Cliente();
			cliente.setId(asientoEntity.get().getId_cliente());
			asiento.setIdCliente(cliente);
			asiento.setValor(asientoEntity.get().getValor());
			if(asientoEntity.get().getFecha_reserva()!=null) {
				asiento.setFechaReserva(asientoEntity.get().getFecha_reserva().toLocalDateTime());
			}
			else {
				asiento.setFechaReserva(null);
			}
		}
		else {
			throw new TransporteException("No se encontro asiento con este id");
		}
		return asiento;
	}
	
	public Long reservarAsientoEvento(Asiento asiento,Long idLocalidad,Long idProducto) throws TransporteException{
		AsientoEventoEntity asientoEventoEntity = new AsientoEventoEntity();
		asientoEventoEntity.setId_localidad(idLocalidad);
		asientoEventoEntity.setId_cliente(asiento.getIdCliente().getId());
		
		if(asiento.getValor()!=null) {
			asientoEventoEntity.setNumero(asiento.getValor());
		}
		
		asientoEventoEntity.setFecha_reserva(new Timestamp(System.currentTimeMillis()));
		asientoEventoEntity.setId_producto(idProducto);
		
		asientoEventoRepository.save(asientoEventoEntity);
		
		return asientoEventoEntity.getId();
		
	}
	
	public void borrarAsiento(Long idLocalidad, Long idAsiento) throws EventoException{
		AsientoEventoEntity asientoEventoEntity = asientoEventoRepository.findAsientoByIdAndId_localidad(idAsiento, idLocalidad);
		if(asientoEventoEntity!=null) {
			asientoEventoRepository.deleteById(idAsiento);
		}
		else {
			throw new EventoException("No Existe el asiento");
		}
	}

}
