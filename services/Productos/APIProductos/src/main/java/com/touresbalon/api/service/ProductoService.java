package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Acomodacion;
import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.Cuarto;
import com.touresbalon.api.domain.DetalleProducto;
import com.touresbalon.api.domain.Evento;
import com.touresbalon.api.domain.Hospedaje;
import com.touresbalon.api.domain.Localidad;
import com.touresbalon.api.domain.Producto;
import com.touresbalon.api.domain.ProductoException;
import com.touresbalon.api.domain.ProductosPSTRs;
import com.touresbalon.api.domain.ReservaExterna;
import com.touresbalon.api.domain.Transporte;
import com.touresbalon.api.repository.ProductoDetalleEntity;
import com.touresbalon.api.repository.ProductoDetalleRepository;
import com.touresbalon.api.repository.ProductoEntity;
import com.touresbalon.api.repository.ProductoRepository;

@ApplicationScoped
@Transactional
public class ProductoService {
	
	@Inject
	ProductoRepository productoRepository;
	
	@Inject
	ProductoDetalleRepository productoDetalleRepository;
	
	@Inject
	EventoService eventoService;
	
	@Inject
	HospedajeService hospedajeService;
	
	@Inject
	TransporteService transporteService;
	
	@Inject
	AsientoService asientoService;
	
	@Inject
	LocalidadService localidadService;
	
	@Inject
	CuartoService cuartoService;
	
 	
	public ProductosPSTRs crearProducto(Producto producto) throws ProductoException{
		
		ProductoEntity productoEntity = new ProductoEntity();
		Producto productoResponse = new Producto();
		List<ReservaExterna> reservas = new ArrayList<>();
		ProductosPSTRs productosPSTRs = new ProductosPSTRs();
		
		if(producto.getCliente()!=null) {
			productoEntity.setId_cliente(producto.getCliente().getId());
		}
		
		if(producto.getDescripcion()!=null) {
			productoEntity.setDescripcion(producto.getDescripcion());
		}
		
		if(producto.getPrecio()!=null) {
			productoEntity.setPrecio(producto.getPrecio());
		}
		else {
			throw new ProductoException("El precio es obligatorio");
		}
		
		productoRepository.save(productoEntity);
		
		productoResponse.setId(productoEntity.getId());
		
		if(producto.getDetalleProducto()!=null) {
			for(DetalleProducto detalleProducto : producto.getDetalleProducto()) {
				if(detalleProducto.getEvento()!=null && detalleProducto.getAsientosEvento()!=null) {
					Evento eventoDetalle = eventoService.consultarPorId(detalleProducto.getEvento().getId());
					if(eventoDetalle!=null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_evento(eventoDetalle.getId());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setAsientos_evento(detalleProducto.getAsientosEvento());
						productoDetalleRepository.save(productoDetalleEntity);
						if(producto.getCliente()!=null) {
							for(int i=1;i<=detalleProducto.getAsientosEvento();i++) {
								if(eventoDetalle.getLocalidades()!=null) {
									Localidad localidad = localidadService.buscarPorId(detalleProducto.getEvento().getLocalidades().get(0).getId());
									if(localidad!=null) {
										Asiento asiento = new Asiento();
										asiento.setIdCliente(producto.getCliente());
										asientoService.reservarAsientoEvento(asiento, localidad.getId());
									}
									else {
										throw new ProductoException("Dado a que es una compra debe especificar en que localidad comprara los asientos para el cliente, la localidad con el id "+eventoDetalle.getLocalidades().get(0).getId()+" no existe");
									}
								}
								else {
									throw new ProductoException("Dado a que es una compra debe especificar en que localidad comprara los asientos para el cliente con el evento "+eventoDetalle.getId());
								}
								
								ReservaExterna reservaExterna = new ReservaExterna();
								reservaExterna.setIdCodigoExterno("ssss");
								reservaExterna.setIdreserva("ssss");
								reservas.add(reservaExterna);
							}
						}
						
					}
					else {
						throw new ProductoException("El evento con id "+detalleProducto.getEvento().getId()+" no existe o no indico la cantidad de asientos del evento ");
					}
				}
				
				if(detalleProducto.getHospedaje()!=null && detalleProducto.getCuartosHospedaje()!=null) {
					Hospedaje hospedajeDetalle = hospedajeService.consultarPorId(detalleProducto.getHospedaje().getCodigo());
					if(hospedajeDetalle!=null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_hospedaje(hospedajeDetalle.getCodigo());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setCuartos_hospedaje(detalleProducto.getCuartosHospedaje());
						productoDetalleRepository.save(productoDetalleEntity);
						if(producto.getCliente()!=null) {
							if(hospedajeDetalle.getAcomodaciones()!=null) {
								for(Acomodacion acomodacion: hospedajeDetalle.getAcomodaciones()) {
									for(Cuarto cuarto : acomodacion.getCuartos()) {
										cuartoService.crearCuarto(cuarto, acomodacion.getId());
									}
									ReservaExterna reservaExterna = new ReservaExterna();
									reservaExterna.setIdCodigoExterno("ssss");
									reservaExterna.setIdreserva("ssss");
									reservas.add(reservaExterna);
								}
							}
							else {
								throw new ProductoException("debe indicar en q  acomodacion realizara la compra");
							}
						}
					}
				}
				
				if(detalleProducto.getTransporte()!=null && detalleProducto.getAsientosTransporte()!=null) {
					Transporte transporteDetalle = transporteService.getTransportePorId(detalleProducto.getTransporte().getId());
					if(transporteDetalle!=null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_transporte(transporteDetalle.getId());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setAsientos_transporte(detalleProducto.getAsientosTransporte());
						productoDetalleRepository.save(productoDetalleEntity);
						if(producto.getCliente()!=null) {
							for(int i=0;i<=detalleProducto.getAsientosTransporte();i++) {
								Asiento asiento = new Asiento();
								asiento.setIdCliente(producto.getCliente());
								asiento.setId(asientoService.crearAsiento(asiento, transporteDetalle.getId()));
							}
							ReservaExterna reservaExterna = new ReservaExterna();
							reservaExterna.setIdCodigoExterno("ssss");
							reservaExterna.setIdreserva("ssss");
							reservas.add(reservaExterna);
						}
					}
				}
			}
		}
		productosPSTRs.setProducto(productoResponse);
		productosPSTRs.setReserva(reservas);
		return productosPSTRs;
	}
	
	
	
}
