package com.touresbalon.ordenes.service;

import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.entities.FacturaEntity;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import com.touresbalon.ordenes.exceptions.KafkaException;
import com.touresbalon.ordenes.exceptions.OrdenException;
import com.touresbalon.ordenes.exceptions.OrdenNotFoundException;
import com.touresbalon.ordenes.helpers.OrdenHelper;
import com.touresbalon.ordenes.helpers.OrdenItemHelper;
import com.touresbalon.ordenes.kafka.EnumOrderAction;
import com.touresbalon.ordenes.kafka.KafkaProducerService;
import com.touresbalon.ordenes.kafka.OrdenMessage;
import com.touresbalon.ordenes.kafka.ReservaMessage;
import com.touresbalon.ordenes.repository.OrdenItemRepository;
import com.touresbalon.ordenes.repository.OrdenRepository;
import com.touresbalon.ordenes.restclient.arm.ARMService;
import com.touresbalon.ordenes.restclient.arm.model.IngresoCompra;
import com.touresbalon.ordenes.restclient.arm.model.RespuestaIngreso;
import com.touresbalon.ordenes.restclient.productos.ProductoService;
import com.touresbalon.ordenes.restclient.productos.model.DetalleProducto;
import com.touresbalon.ordenes.restclient.productos.model.Producto;
import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRq;
import com.touresbalon.ordenes.restclient.validacliente.ClienteService;
import com.touresbalon.ordenes.restclient.validacliente.model.ClientesGETByIdRs;
import com.touresbalon.ordenes.restclient.validatarjeta.TarjetaService;
import com.touresbalon.ordenes.restclient.validatarjeta.model.*;
import com.touresbalon.ordenes.util.EnumTipoProducto;
import com.touresbalon.ordenes.util.EnumValidacionCliente;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
@Transactional
public class OrdenService {

    private static final Logger log = LoggerFactory.getLogger(OrdenService.class);

    @Inject
    EntityManager em;

    @Inject
    OrdenRepository ordenRepository;

    @Inject
    OrdenItemRepository ordenItemRepository;

    @Inject
    @RestClient
    TarjetaService validaTarjetaService;

    @Inject
    @RestClient
    ProductoService productoService;

    @Inject
    @RestClient
    ClienteService clienteService;

    @Inject
    @RestClient
    ARMService aRMService;

    @Inject
    KafkaProducerService kafkaProducerService;

    private static final BigDecimal MONTO_ESTABLECIDO = new BigDecimal(10000000);

    /**
     * Realizar compra
     *
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden realizarCompra(Orden orden) throws OrdenException {
        log.info("realizarCompra(Orden orden) ");
        //Llamar servicio de consultar cliente
        Cliente cliente = consultarCliente(orden.getCodigoCliente());

        //Llamar servicio de confirmacion de tarjeta
        String uuidPago = validarTarjeta(orden, cliente);
        if (uuidPago != null) {
            EnumValidacionCliente validacionCliente = validarCliente(cliente, orden.getValorTotal());
            if (!validacionCliente.equals(EnumValidacionCliente.ENUM_VALIDACION_RECHAZADA)) {
                orden.setEstado(Orden.EstadoEnum.EN_APROBACION);
                orden.setCodigo(getCodigo());
                orden.setFechaCreacion(LocalDateTime.now());
                orden.setFechaModificacion(LocalDateTime.now());

                if (validacionCliente.equals(EnumValidacionCliente.ENUM_VALIDACION_EXITOSA)) {
                    orden.setEstado(Orden.EstadoEnum.EN_RESERVA);
                    //Llamado a realizar Reservas
                    realizarReservas(orden, cliente);
                }
                OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
                ordenEntity.setUidPago(uuidPago);

                enviarOrdenAKafka(ordenEntity, orden, null, EnumOrderAction.CREACION, null);
                /*try {
                    Vertx vertx = Vertx.vertx();
                    KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(
                            "read-order", objectMapper.writeValueAsString(orden));

                    // use producer for interacting with Apache Kafka
                    producer = KafkaProducer.create(vertx, config);
                    producer.send(record, done -> {

                        if (done.succeeded()) {

                            RecordMetadata recordMetadata = done.result();
                            System.out.println("Message " + record + " written on topic=" + recordMetadata.getTopic() +
                                    ", partition=" + recordMetadata.getPartition() +
                                    ", offset=" + recordMetadata.getOffset());
                            producer.close();
                            //vertx.close();
                        }

                    });
                } catch (JsonProcessingException e) {
                    log.error("Error en serializacion ", e);
                }*/
            }
            return orden;

        } else {
            throw new OrdenException("No paso la validacion de cupo de la tarjeta");
        }

    }

    /**
     * Aprobar orden
     *
     * @param codigoOrden
     * @return Datos de orden actualizada
     * @throws OrdenException
     */
    public Orden aprobarOrden(Long codigoOrden) {
        log.info("aprobarOrden(Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", Sort.by("id").descending(), codigoOrden).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            Orden orden = OrdenHelper.ordenEntityToOrden(ordenEntityOpt.get(), null);

            //Actualiza orden
            OrdenEntity ordenEntityMod = new OrdenEntity();
            orden.setEstado(Orden.EstadoEnum.EN_RESERVA);
            orden.setFechaModificacion(LocalDateTime.now());
            ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityMod);
            ordenEntityMod.setUidPago(ordenEntityOpt.get().getUidPago());

            //Consulta el cliente
            Cliente cliente = consultarCliente(orden.getCodigoCliente());

            //Llamado a registrar el producto ahora kafka
            realizarReservas(orden, cliente);

            enviarOrdenAKafka(ordenEntityMod, orden, null, EnumOrderAction.ACTUALIZACION, null);
            return orden;
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }
    }

    /**
     * Actualizar Items orden
     *
     * @param reserva
     * @return Datos de orden actualizada
     * @throws OrdenException
     */
    public Orden actualizarOrdenItems(ReservaMessage reserva) {
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", Sort.by("id").descending(), reserva.getIdOrden()).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            Orden orden = OrdenHelper.ordenEntityToOrden(ordenEntityOpt.get(), null);

            //Actualiza orden
            OrdenEntity ordenEntityMod = new OrdenEntity();
            orden.setFechaModificacion(LocalDateTime.now());
            ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityMod);
            ordenEntityMod.setUidPago(ordenEntityOpt.get().getUidPago());
            ordenEntityMod.setCodigoProducto(reserva.getIdProducto());
            List<OrdenItem> items = new ArrayList<>();
            List<OrdenItemEntity> itemsEntity = new ArrayList<>();
            OrdenItem ordenItem = new OrdenItem(reserva.getIdProductoDetalle(), reserva.getCantidadProductosReserva(),
                    reserva.getTipoProducto());
            itemsEntity.add(OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem
                    , null));
            items.add(ordenItem);

            enviarOrdenAKafka(ordenEntityMod, orden, itemsEntity, EnumOrderAction.AGREGAR_ITEM, items);
            return orden;
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }
    }

    /**
     * Realiza validacion del cliente
     *
     * @param cliente
     * @return 1 si resulta exitoso, 0 si no es exitoso y 2 si queda en revision
     */
    private EnumValidacionCliente validarCliente(Cliente cliente, BigDecimal valorTotal) {
        log.info("validarCliente(Cliente cliente) ");
        EnumValidacionCliente respuesta = EnumValidacionCliente.ENUM_VALIDACION_RECHAZADA;
        if (cliente.getCategoria().getNombre().equalsIgnoreCase("Platino")) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if (cliente.getCategoria().getNombre().equalsIgnoreCase("Dorado")) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if (cliente.getCategoria().getNombre().equalsIgnoreCase("Dorado") &&
                valorTotal.compareTo(MONTO_ESTABLECIDO) < 1) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if (cliente.getCategoria().getNombre().equalsIgnoreCase("Plateado")) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_PENDIENTE;
        }
        return respuesta;
    }

    /**
     * Realiza consulta del cliente
     *
     * @param codigoCliente
     * @return Cliente si resulta exitoso
     */
    private Cliente consultarCliente(Long codigoCliente) {
        log.info("consultarCliente(Long codigoCliente) ");
        Cliente cliente;
        ClientesGETByIdRs clientesGETByIdRs = null;
        try {
            //clientesGETByIdRs = clienteService.validarCliente(codigoCliente);
        } catch (Exception e) {
            log.error("validarCliente(Long codigoCliente) ", e);
        }
        if (clientesGETByIdRs != null) {
            cliente = clientesGETByIdRs.getCliente();
        } else {
            cliente = new Cliente();
            cliente.setId(codigoCliente);
            TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
            tipoIdentificacion.setCodigo("CC");
            cliente.setTipoIdentificacion(tipoIdentificacion);
			cliente.setIdentificacion("332232323");
            Categoria categoria = new Categoria();
            categoria.setNombre("Platino");
            cliente.setCategoria(categoria);
        }
        return cliente;
    }

    /**
     * LLamada a servicio de validar tarjeta
     *
     * @param orden
     * @param cliente
     * @return Exitoso si pasa la validacion
     */
    private String validarTarjeta(Orden orden, Cliente cliente) {
        log.info("validarTarjeta (Orden orden, Cliente cliente) ");
        CompraValidacion compraValidacion = new CompraValidacion();
        compraValidacion.setMonto(orden.getValorTotal());
        TipoIdentificacionValidacion tipoIdentificacion = new TipoIdentificacionValidacion
                (cliente.getTipoIdentificacion().getCodigo().toString(), cliente.getTipoIdentificacion().getNombre());
        compraValidacion.setCliente(new ClienteValidacion(cliente.getIdentificacion(),
                cliente.getNombres(), cliente.getApellidos(), cliente.getCelular(), tipoIdentificacion));
        compraValidacion.setTarjeta(new TarjetaValidacion(orden.getTarjeta().getNumero(), TarjetaValidacion.TipoEnum.fromValue(orden.getTarjeta().getTipo().name())));
        RespuestaValidacion respuestaValidacion = validaTarjetaService.validarTarjeta(compraValidacion);
        if (respuestaValidacion.getCodigo().equals(EnumRespuestaValidacion.RESPUESTA_EXITOSA.getCodigo())) {
            return respuestaValidacion.getUidPago();
        }
        return null;
    }

    /**
     * Realizar las reservas
     *
     * @param orden
     * @param cliente
     */
    public void realizarReservas(Orden orden, Cliente cliente) throws OrdenException {
        log.info("realizarReservas(Orden orden, Cliente cliente) ");
        ProductosPSTRq productosPSTRq = new ProductosPSTRq();
        Producto producto = new Producto();
        producto.setCliente(cliente);
        //producto.setFechaCreacion(OffsetDateTime.now());
        producto.setPrecio(orden.getValorTotal().doubleValue());
        List<DetalleProducto> detalleProductosList = new ArrayList<>();
        if (orden.getHospedaje() != null && !orden.getHospedaje().isEmpty()) {
            for (Hospedaje hospedaje :
                    orden.getHospedaje()) {
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setHospedaje(hospedaje);
                detalleProducto.setCuartosHospedaje(hospedaje.getCantidadCuartos());
                detalleProducto.setId(new Random().nextLong());
                detalleProductosList.add(detalleProducto);
            }
        }
        if (orden.getEvento() != null && !orden.getEvento().isEmpty()) {
            producto.setDescripcion(orden.getEvento().get(0).getNombre());
            for (Evento evento :
                    orden.getEvento()) {
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setEventos(evento);
                detalleProducto.setAsietosEvento(evento.getCantidad());
                detalleProducto.setId(new Random().nextLong());
                detalleProductosList.add(detalleProducto);
            }
        }
        if (orden.getTransporte() != null && !orden.getTransporte().isEmpty()) {
            for (Transporte transporte :
                    orden.getTransporte()) {
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setTransporte(transporte);
                detalleProducto.setAsietosTransporte(transporte.getCantidadCupos());
                detalleProducto.setId(new Random().nextLong());
                detalleProductosList.add(detalleProducto);
            }
        }
        producto.setDetalleProducto(detalleProductosList);
		producto.setIdOrden(orden.getCodigo());
        productosPSTRq.setProducto(producto);
        //Response response = productoService.realizarReservas(productosPSTRq);
        //if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
          //  producto = ((ProductosPSTRs) response.getEntity()).getProducto();
        //}
        try {
            //Publish to kafka to persist in read table
            // only topic and message value are specified, round robin on destination partitions
            // send Item list
            kafkaProducerService.sendProductsToKafka(productosPSTRq);
        } catch (KafkaException e) {
            log.error("Error en envio de orden ", e);
        }
    }

    private Long getCodigo() {
        BigInteger codigo = (BigInteger) em.createNativeQuery("SELECT nextval('public.seq');").getSingleResult();
        log.info("codigo " + codigo);
        return codigo.longValue();
    }

    /**
     * Crea una orden
     *
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden crearOrden(Orden orden) throws OrdenException {
        log.info("crearOrden(Orden orden) ");
        OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
        ordenEntity.setCodigo(getCodigo());
        ordenEntity.setFechaCreacion(LocalDateTime.now());
        ordenEntity.setFechaModificacion(LocalDateTime.now());

        orden = OrdenHelper.ordenEntityToOrden(ordenEntity, orden);
        enviarOrdenAKafka(ordenEntity, orden, null, EnumOrderAction.CREACION, null);

        return orden;
    }

    /**
     * @param orden
     * @param codigoOrden
     * @return
     * @throws OrdenException
     */
    public void actualizarOrden(Orden orden, Long codigoOrden) throws OrdenException {
        log.info("actualizarOrden(Orden orden, Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", Sort.by("id").descending(), codigoOrden).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            orden.setFechaModificacion(LocalDateTime.now());
            orden.setCodigo(codigoOrden);
            orden.setCodigoCliente(ordenEntityOpt.get().getCodigoCliente());
            orden.setFechaCreacion(ordenEntityOpt.get().getFechaCreacion());
            orden.setFechaModificacion(LocalDateTime.now());
            OrdenEntity ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, null);
            ordenEntityMod.setCodigoProducto(ordenEntityOpt.get().getCodigoProducto());
            ordenEntityMod.setUidPago(ordenEntityOpt.get().getUidPago());

            List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
            enviarOrdenAKafka(ordenEntityMod, orden, items, EnumOrderAction.ACTUALIZACION, null);
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }
    }

    /**
     * Cancela una orden
     *
     * @param codigoOrden
     * @return
     * @throws OrdenException
     */
    public boolean cancelarOrden(Long codigoOrden) throws OrdenException {
        log.info("cancelarOrden(Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", Sort.by("id").descending(), codigoOrden).firstResultOptional();
        Orden ordenResponse = new Orden();
        if (ordenEntityOpt.isPresent()) {
            ordenResponse = OrdenHelper.ordenEntityToOrden(ordenEntityOpt.get(), ordenResponse);
            ordenResponse.setFechaModificacion(LocalDateTime.now());
            ordenResponse.setEstado(Orden.EstadoEnum.CANCELADA);
            OrdenEntity ordenEntityMod = OrdenHelper.ordenToOrdenEntity(ordenResponse, null);
            ordenEntityMod.setCodigoProducto(ordenEntityOpt.get().getCodigoProducto());
            ordenEntityMod.setUidPago(ordenEntityOpt.get().getUidPago());

            List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
            enviarOrdenAKafka(ordenEntityMod, ordenResponse, items, EnumOrderAction.ACTUALIZACION, null);
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }

        return true;
    }

    /**
     * Metodo encargado de enviar orden para persistirla en la bd de lectura
     * @param ordenEntityMod
     * @param orden
     * @param itemsEntity
     * @param action
     * @param items
     */
    private void enviarOrdenAKafka(OrdenEntity ordenEntityMod, Orden orden, List<OrdenItemEntity> itemsEntity,
                                   EnumOrderAction action, List<OrdenItem> items){
        log.info("enviarOrdenAKafka(OrdenEntity " + ordenEntityMod +" itemsEntity " + itemsEntity +") ");
        try {
            //Publish to kafka to persist in read table
            // only topic and message value are specified, round robin on destination partitions
            OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
            ordenMessage.setUidPago(ordenEntityMod.getUidPago());
            ordenMessage.setCodigoProducto(ordenEntityMod.getCodigoProducto());
            ordenMessage.setAccion(action);
            ordenMessage.setItems(items);
            kafkaProducerService.sendOrderToKafka(ordenMessage);
            ordenEntityMod.setEnviado(true);
        } catch (KafkaException e) {
            log.error("Error en envio de orden ", e);
        } finally {
            ordenRepository.persist(ordenEntityMod);
            //persistir items de nuevo
            if(itemsEntity != null) {
                for (OrdenItemEntity item :
                        itemsEntity) {
					try {
						log.info("item " + item );
						OrdenItem ordenItem = OrdenItemHelper.ordenItemEntityToOrdenItem(item, null);
						OrdenItemEntity itemdup = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem, null);
						itemdup.setOrden(ordenEntityMod);					
						log.info("ordenEntityMod " + ordenEntityMod );
						ordenItemRepository.persist(itemdup);	
						log.info("itemdup " + itemdup );
					} catch (Exception e) {
						log.error("Error creando item ", e);
					} 
                }
            }
        }
    }

    /**
     * Valida las reservas
     *
     * @param reservaMessage
     * @return Datos de orden actualizada
     * @throws OrdenException
     */
    public Orden validarReserva(ReservaMessage reservaMessage) {
        log.info("validarReserva(ReservaMessage reservaMessage) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", Sort.by("id").descending(), reservaMessage.getIdOrden()).firstResultOptional();
        Orden ordenResponse = new Orden();
        if (ordenEntityOpt.isPresent()) {
            //Consulto item
            List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
            if(!items.isEmpty()) {
                ordenResponse = OrdenHelper.ordenEntityToOrden(ordenEntityOpt.get(), ordenResponse);
                FacturaEntity factura = null;
                boolean reservaCompleta = true;
                boolean existeReservaRechazada = false;
                for (OrdenItemEntity item :
                        items) {
                    if (item.getCodigo().equals(reservaMessage.getIdProductoDetalle())) {
                        item.setCodigoReserva(reservaMessage.getIdProductoDetalle().toString());
                    }
                    if(item.getCodigoReserva() == null) {
                        reservaCompleta = false;
                    }
                    if(item.getCodigoReserva().equalsIgnoreCase("RECHAZADA")) {
                        existeReservaRechazada = true;
                    }
                }
                if(reservaCompleta && !existeReservaRechazada) {
                    //Llama servicio de registrar compra a mock de pago y mock de arm
                    AprobacionCompra aprobacionCompra = new AprobacionCompra();
                    aprobacionCompra.setNumeroTarjeta(ordenEntityOpt.get().getTarjetas().get(0).getNumero());
                    aprobacionCompra.setUidPago(ordenEntityOpt.get().getUidPago());
                    validaTarjetaService.aprobarPago(aprobacionCompra);

                    Cliente cliente = consultarCliente(ordenResponse.getCodigoCliente());
                    IngresoCompra ingresoCompra = new IngresoCompra();
                    ingresoCompra.setMontoTotal(ordenResponse.getValorTotal());
                    ingresoCompra.setNumeroIdentificacion(cliente.getIdentificacion());
                    ingresoCompra.setTipoIdentificacion(cliente.getTipoIdentificacion().getCodigo());
                    ingresoCompra.setNumeroTarjeta(aprobacionCompra.getNumeroTarjeta());
                    ingresoCompra.setUidPago(ordenEntityOpt.get().getUidPago());
                    RespuestaIngreso respuestaIngreso = aRMService.ingresarCompra(ingresoCompra);

                    //Genera factura
                    factura = new FacturaEntity();
                    factura.setCodigo(respuestaIngreso.getUidPago());
                    factura.setFechaCreacion(LocalDateTime.now());
                    factura.setValorTotal(ordenResponse.getValorTotal());

                    ordenResponse.setEstado(Orden.EstadoEnum.PAGADA);
                } else if(reservaCompleta && existeReservaRechazada){
                    ordenResponse.setEstado(Orden.EstadoEnum.RESERVA_RECHAZADA);
                }
                ordenResponse.setFechaModificacion(LocalDateTime.now());
                OrdenEntity ordenEntityMod = OrdenHelper.ordenToOrdenEntity(ordenResponse, null);
                ordenEntityMod.setCodigoProducto(ordenEntityOpt.get().getCodigoProducto());
                ordenEntityMod.setUidPago(ordenEntityOpt.get().getUidPago());

                enviarOrdenAKafka(ordenEntityMod, ordenResponse, items, EnumOrderAction.ACTUALIZACION, null);
                if(factura != null) {
                    factura.setOrden(ordenEntityMod);
                    FacturaEntity.persist(factura);
                }
            }
            return ordenResponse;
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }
    }
}