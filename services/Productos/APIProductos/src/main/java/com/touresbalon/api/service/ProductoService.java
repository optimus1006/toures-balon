package com.touresbalon.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.touresbalon.api.domain.Acomodacion;
import com.touresbalon.api.domain.Asiento;
import com.touresbalon.api.domain.Cliente;
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
import com.touresbalon.api.kafka.EnumTipoProducto;
import com.touresbalon.api.kafka.KafkaConsumer;
import com.touresbalon.api.kafka.KafkaProducerService;
import com.touresbalon.api.kafka.ReservaMessage;
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

	@Inject
	AcomodacionService acomodacionService;
	
	@Inject
	KafkaProducerService kafkaProducer;

	public ProductosPSTRs crearProducto(Producto producto) throws ProductoException {

		ProductoEntity productoEntity = new ProductoEntity();
		Producto productoResponse = new Producto();
		List<ReservaExterna> reservas = new ArrayList<>();
		ProductosPSTRs productosPSTRs = new ProductosPSTRs();

		if (producto.getCliente() != null) {
			productoEntity.setId_cliente(producto.getCliente().getId());
		}

		if (producto.getDescripcion() != null) {
			productoEntity.setDescripcion(producto.getDescripcion());
		}

		if (producto.getPrecio() != null) {
			productoEntity.setPrecio(producto.getPrecio());
		} else {
			throw new ProductoException("El precio es obligatorio");
		}

		productoEntity.setFecha_creacion(new Timestamp(System.currentTimeMillis()));

		productoEntity.setIdOrden(producto.getIdOrden());

		productoRepository.save(productoEntity);

		productoResponse.setId(productoEntity.getId());

		if (producto.getDetalleProducto() != null) {
			for (DetalleProducto detalleProducto : producto.getDetalleProducto()) {
				if (detalleProducto.getEvento() != null && detalleProducto.getAsientosEvento() != null) {
					Evento eventoDetalle = eventoService.consultarPorId(detalleProducto.getEvento().getId());
					if (eventoDetalle != null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_evento(eventoDetalle.getId());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setAsientos_evento(detalleProducto.getAsientosEvento());
						productoDetalleRepository.save(productoDetalleEntity);
						if (producto.getCliente() != null) {

							ReservaExterna reservaExterna = new ReservaExterna();
							reservas.add(reservaExterna);
							ReservaMessage reservaMessage = new ReservaMessage();
							reservaMessage.setCantidadProductosReserva(detalleProducto.getAsientosEvento());
							reservaMessage.setCliente(producto.getCliente());
							reservaMessage.setCodigoExterno(eventoDetalle.getCodigoExterno());
							reservaMessage.setIdConvenio(eventoDetalle.getConvenio().getIdentificacion());
							reservaMessage.setIdOrden(producto.getIdOrden());
							reservaMessage.setIdProducto(productoEntity.getId());
							reservaMessage.setIdProductoDetalle(productoDetalleEntity.getId());
							reservaMessage.setTipoProducto("2");

							for (int i = 1; i <= detalleProducto.getAsientosEvento(); i++) {
								if (eventoDetalle.getLocalidades() != null) {
									Localidad localidad = localidadService
											.buscarPorId(detalleProducto.getEvento().getLocalidades().get(0).getId());
									if (localidad != null) {
										reservaMessage.setCodigoExternoDetalle(localidad.getCodigoExterno());
										Asiento asiento = new Asiento();
										asiento.setIdCliente(producto.getCliente());
										asientoService.reservarAsientoEvento(asiento, localidad.getId(),
												productoEntity.getId());
									} else {
										throw new ProductoException(
												"Dado a que es una compra debe especificar en que localidad comprara los asientos para el cliente, la localidad con el id "
														+ eventoDetalle.getLocalidades().get(0).getId() + " no existe");
									}
								} else {
									throw new ProductoException(
											"Dado a que es una compra debe especificar en que localidad comprara los asientos para el cliente con el evento "
													+ eventoDetalle.getId());
								}
							}

							try {
								kafkaProducer.sendOrderToKafka(reservaMessage);
							}catch (Exception e) {
								System.out.println("Message"+e.getMessage());
		                		System.out.println("Cause"+e.getCause());
		                		System.out.println("Trace"+e.getStackTrace());
		                		System.out.println(e);
							}
							

						}

					} else {
						throw new ProductoException("El evento con id " + detalleProducto.getEvento().getId()
								+ " no existe o no indico la cantidad de asientos del evento ");
					}
				}

				if (detalleProducto.getHospedaje() != null && detalleProducto.getCuartosHospedaje() != null) {
					Hospedaje hospedajeDetalle = hospedajeService
							.consultarPorId(detalleProducto.getHospedaje().getCodigo());
					if (hospedajeDetalle != null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_hospedaje(hospedajeDetalle.getCodigo());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setCuartos_hospedaje(detalleProducto.getCuartosHospedaje());
						productoDetalleRepository.save(productoDetalleEntity);

						if (producto.getCliente() != null) {
							if (detalleProducto.getHospedaje().getAcomodaciones() != null) {
								ReservaExterna reservaExterna = new ReservaExterna();
								reservas.add(reservaExterna);
								ReservaMessage reservaMessage = new ReservaMessage();
								reservaMessage.setCantidadProductosReserva(detalleProducto.getCuartosHospedaje());
								reservaMessage.setCliente(producto.getCliente());
								reservaMessage.setCodigoExterno(hospedajeDetalle.getCodigoExterno());
								reservaMessage.setIdConvenio(hospedajeDetalle.getConvenio().getIdentificacion());
								reservaMessage.setIdOrden(producto.getIdOrden());
								reservaMessage.setIdProducto(productoEntity.getId());
								reservaMessage.setIdProductoDetalle(productoDetalleEntity.getId());
								reservaMessage.setTipoProducto("1");
								for (Acomodacion acomodacion : detalleProducto.getHospedaje().getAcomodaciones()) {
									Acomodacion acomodacionDetalle = acomodacionService.buscarAcomodacionPorId(acomodacion.getId());
									if (acomodacionDetalle != null) {
										reservaMessage.setCodigoExternoDetalle(acomodacionDetalle.getCodigoExterno());
										for (Cuarto cuarto : acomodacion.getCuartos()) {
											cuarto.setCliente(producto.getCliente());
											cuartoService.crearCuarto(cuarto, acomodacionDetalle.getId(),
													productoEntity.getId());
										}
									} else {
										throw new ProductoException(
												"la acomodacion con el id " + acomodacion.getId() + " no existe.");
									}
								}
								kafkaProducer.sendOrderToKafka(reservaMessage);
								reservas.add(reservaExterna);
							} else {
								throw new ProductoException("debe indicar en que  acomodacion realizara la compra");
							}
						}
					}
				}

				if (detalleProducto.getTransporte() != null && detalleProducto.getAsientosTransporte() != null) {
					Transporte transporteDetalle = transporteService
							.getTransportePorId(detalleProducto.getTransporte().getId());
					if (transporteDetalle != null) {
						ProductoDetalleEntity productoDetalleEntity = new ProductoDetalleEntity();
						productoDetalleEntity.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						productoDetalleEntity.setId_transporte(transporteDetalle.getId());
						productoDetalleEntity.setId_producto(productoEntity.getId());
						productoDetalleEntity.setAsientos_transporte(detalleProducto.getAsientosTransporte());
						productoDetalleRepository.save(productoDetalleEntity);
						if (producto.getCliente() != null) {
							ReservaMessage reservaMessage = new ReservaMessage();
							reservaMessage.setCantidadProductosReserva(detalleProducto.getAsientosTransporte());
							reservaMessage.setCliente(producto.getCliente());
							reservaMessage.setCodigoExterno(transporteDetalle.getCodigoExterno());
							reservaMessage.setIdConvenio(transporteDetalle.getConvenio().getIdentificacion());
							reservaMessage.setIdOrden(producto.getIdOrden());
							reservaMessage.setIdProducto(productoEntity.getId());
							reservaMessage.setIdProductoDetalle(productoDetalleEntity.getId());
							reservaMessage.setTipoProducto("0");
							for (int i = 1; i <= detalleProducto.getAsientosTransporte(); i++) {
								Asiento asiento = new Asiento();
								asiento.setIdCliente(producto.getCliente());
								asiento.setId(asientoService.crearAsiento(asiento, transporteDetalle.getId(),
										productoEntity.getId()));
							}
							kafkaProducer.sendOrderToKafka(reservaMessage);
							ReservaExterna reservaExterna = new ReservaExterna();
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

	public List<Producto> listarProductos(Long idCliente, Long id) {
		List<Producto> productos = new ArrayList<>();
		if (idCliente != null && id == null) {
			List<ProductoEntity> productosEntity = productoRepository.findById_cliente(idCliente);
			if (productosEntity != null) {
				for (ProductoEntity productoEntity : productosEntity) {
					Producto producto = new Producto();
					List<DetalleProducto> detalleProductos = new ArrayList<>();
					Cliente cliente = new Cliente();
					cliente.setId(productoEntity.getId_cliente());
					producto.setCliente(cliente);
					producto.setDescripcion(productoEntity.getDescripcion());
					producto.setFechaCreacion(productoEntity.getFecha_creacion().toLocalDateTime());
					producto.setId(productoEntity.getId());
					producto.setPrecio(productoEntity.getPrecio());
					List<ProductoDetalleEntity> productosDetalleEntity = productoDetalleRepository
							.findById_producto(producto.getId());
					for (ProductoDetalleEntity productoDetalleEntity : productosDetalleEntity) {
						DetalleProducto detalleProducto = new DetalleProducto();
						if (productoDetalleEntity.getId_evento() != null) {
							detalleProducto.setAsientosEvento(productoDetalleEntity.getAsientos_evento());
							Evento evento = eventoService.consultarPorId(productoDetalleEntity.getId_evento());
							List<Localidad> localidadesEvento = new ArrayList<>();
							List<Localidad> localidades = evento.getLocalidades();
							for (Localidad localidad : localidades) {
								List<Asiento> asientosEvento = asientoService.buscarPorEvento(localidad.getId(), null,
										producto.getId());
								if (asientosEvento.size() > 0) {
									localidad.setAsientos(asientosEvento);
									localidadesEvento.add(localidad);
								}
							}
							detalleProducto.setEvento(evento);
						}
						if (productoDetalleEntity.getId_hospedaje() != null) {
							detalleProducto.setCuartosHospedaje(productoDetalleEntity.getCuartos_hospedaje());
							Hospedaje hospedaje = hospedajeService
									.consultarPorId(productoDetalleEntity.getId_hospedaje());
							detalleProducto.setHospedaje(hospedaje);
						}
						if (productoDetalleEntity.getId_transporte() != null) {
							detalleProducto.setAsientosTransporte(productoDetalleEntity.getAsientos_transporte());
							Transporte transporte = transporteService
									.getTransportePorId(productoDetalleEntity.getId_transporte());
							detalleProducto.setTransporte(transporte);
						}
						detalleProductos.add(detalleProducto);
					}
					producto.setDetalleProducto(detalleProductos);
					productos.add(producto);
				}
			}
			return productos;
		} else if (id != null) {
			Optional<ProductoEntity> productosEntity = productoRepository.findById(id);
			if (productosEntity.isPresent()) {
					Producto producto = new Producto();
					List<DetalleProducto> detalleProductos = new ArrayList<>();
					Cliente cliente = new Cliente();
					cliente.setId(productosEntity.get().getId_cliente());
					producto.setCliente(cliente);
					producto.setDescripcion(productosEntity.get().getDescripcion());
					producto.setFechaCreacion(productosEntity.get().getFecha_creacion().toLocalDateTime());
					producto.setId(productosEntity.get().getId());
					producto.setPrecio(productosEntity.get().getPrecio());
					List<ProductoDetalleEntity> productosDetalleEntity = productoDetalleRepository
							.findById_producto(producto.getId());
					for (ProductoDetalleEntity productoDetalleEntity : productosDetalleEntity) {
						DetalleProducto detalleProducto = new DetalleProducto();
						if (productoDetalleEntity.getId_evento() != null) {
							detalleProducto.setAsientosEvento(productoDetalleEntity.getAsientos_evento());
							Evento evento = eventoService.consultarPorId(productoDetalleEntity.getId_evento());
							detalleProducto.setEvento(evento);
						}
						if (productoDetalleEntity.getId_hospedaje() != null) {
							detalleProducto.setCuartosHospedaje(productoDetalleEntity.getCuartos_hospedaje());
							Hospedaje hospedaje = hospedajeService
									.consultarPorId(productoDetalleEntity.getId_hospedaje());
							detalleProducto.setHospedaje(hospedaje);
						}
						if (productoDetalleEntity.getId_transporte() != null) {
							detalleProducto.setAsientosTransporte(productoDetalleEntity.getAsientos_transporte());
							Transporte transporte = transporteService
									.getTransportePorId(productoDetalleEntity.getId_transporte());
							detalleProducto.setTransporte(transporte);
						}
						detalleProductos.add(detalleProducto);
					}
					producto.setDetalleProducto(detalleProductos);
					productos.add(producto);
			}
			return productos;
		} else {
			Iterable<ProductoEntity> productosEntity = productoRepository.findAll();
			if (productosEntity != null) {
				for (ProductoEntity productoEntity : productosEntity) {
					Producto producto = new Producto();
					List<DetalleProducto> detalleProductos = new ArrayList<>();
					Cliente cliente = new Cliente();
					cliente.setId(productoEntity.getId_cliente());
					producto.setCliente(cliente);
					producto.setDescripcion(productoEntity.getDescripcion());
					producto.setFechaCreacion(productoEntity.getFecha_creacion().toLocalDateTime());
					producto.setId(productoEntity.getId());
					producto.setPrecio(productoEntity.getPrecio());
					List<ProductoDetalleEntity> productosDetalleEntity = productoDetalleRepository
							.findById_producto(producto.getId());
					for (ProductoDetalleEntity productoDetalleEntity : productosDetalleEntity) {
						DetalleProducto detalleProducto = new DetalleProducto();
						if (productoDetalleEntity.getId_evento() != null) {
							detalleProducto.setAsientosEvento(productoDetalleEntity.getAsientos_evento());
							Evento evento = eventoService.consultarPorId(productoDetalleEntity.getId_evento());
							detalleProducto.setEvento(evento);
						}
						if (productoDetalleEntity.getId_hospedaje() != null) {
							detalleProducto.setCuartosHospedaje(productoDetalleEntity.getCuartos_hospedaje());
							Hospedaje hospedaje = hospedajeService
									.consultarPorId(productoDetalleEntity.getId_hospedaje());
							detalleProducto.setHospedaje(hospedaje);
						}
						if (productoDetalleEntity.getId_transporte() != null) {
							detalleProducto.setAsientosTransporte(productoDetalleEntity.getAsientos_transporte());
							Transporte transporte = transporteService
									.getTransportePorId(productoDetalleEntity.getId_transporte());
							detalleProducto.setTransporte(transporte);
						}
						detalleProductos.add(detalleProducto);
					}
					producto.setDetalleProducto(detalleProductos);
					productos.add(producto);
				}
			}
			return productos;
		}

	}

}
