package com.touresbalon.ordenes.service;

import com.touresbalon.ordenes.api.model.*;
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
import com.touresbalon.ordenes.repository.OrdenItemRepository;
import com.touresbalon.ordenes.repository.OrdenRepository;
import com.touresbalon.ordenes.restclient.productos.ProductoService;
import com.touresbalon.ordenes.restclient.productos.model.DetalleProducto;
import com.touresbalon.ordenes.restclient.productos.model.Producto;
import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRq;
import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRs;
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
import javax.ws.rs.core.Response;
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
                orden.setEstado(Orden.EstadoEnum.EN_RESERVA);
                orden.setCodigo(getCodigo());
                orden.setFechaCreacion(LocalDateTime.now());
                orden.setFechaModificacion(LocalDateTime.now());

                OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
                ordenEntity.setUidPago(uuidPago);

                if (validacionCliente.equals(EnumValidacionCliente.ENUM_VALIDACION_EXITOSA)) {
                    ordenEntity.setEnviado(true);
                    ordenRepository.persist(ordenEntity);
                    orden = aprobarOrden(orden, true, cliente);
                } else {
                    try {
                        //Publish to kafka to persist in read table
                        // only topic and message value are specified, round robin on destination partitions
                        // sen Item list
                        OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
                        ordenMessage.setAccion(EnumOrderAction.PAGO);
                        ordenMessage.setUidPago(ordenEntity.getUidPago());
                        kafkaProducerService.sendOrderToKafka(ordenMessage);
                        ordenEntity.setEnviado(true);
                    } catch (KafkaException e) {
                        log.error("Error en envio de orden " , e);
                    } finally {
                        ordenRepository.persist(ordenEntity);
                    }
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
            }
            return orden;

        } else {
            throw new OrdenException("No paso la validacion de cupo de la tarjeta");
        }

    }

    /**
     * Aprobar orden
     * @param orden
     * @param enPago
     * @param cliente
     * @return Datos de orden actualizada
     * @throws OrdenException
     */
    public Orden aprobarOrden(Orden orden, boolean enPago, Cliente cliente) throws OrdenException {
        //Llamar servicio de productos para reservar
        if (cliente == null) {
            //Llamar servicio de consultar cliente
            cliente = consultarCliente(orden.getCodigoCliente());
        }
        //Llamado a registrar el producto
        Producto producto = realizarReservas(orden, cliente);
        if (producto != null) {
            OrdenEntity ordenEntityMod = new OrdenEntity();
            ordenEntityMod.setCodigoProducto(producto.getId());
            ordenEntityMod.setFechaPago(LocalDateTime.now());
            orden.setEstado(Orden.EstadoEnum.PAGADA);
            ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityMod);
            List<OrdenItem> items = new ArrayList<>();
            List<OrdenItemEntity> itemsEntity = new ArrayList<>();
            for (DetalleProducto detalleProducto : producto.getDetalleProducto()
            ) {
                EnumTipoProducto tipoProducto = null;
                Integer cantidad = null;
                if (detalleProducto.getEventos() != null) {
                    tipoProducto = EnumTipoProducto.EVENTO;
                    cantidad = detalleProducto.getAsietosEvento();
                } else if (detalleProducto.getHospedaje() != null) {
                    tipoProducto = EnumTipoProducto.HOSPEDAJE;
                    cantidad = detalleProducto.getCuartosHospedaje();
                } else if (detalleProducto.getTransporte() != null) {
                    tipoProducto = EnumTipoProducto.TRANSPORTE;
                    cantidad = detalleProducto.getAsietosTransporte();
                }
                OrdenItem ordenItem = new OrdenItem(detalleProducto.getId(), cantidad, tipoProducto);
                itemsEntity.add(OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem
                        , null));
                items.add(ordenItem);
            }

            try {
                //Publish to kafka to persist in read table
                // only topic and message value are specified, round robin on destination partitions
                // sen Item list
                OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
                ordenMessage.setAccion(EnumOrderAction.PAGO);
                if(!enPago) {
                    ordenMessage.setAccion(EnumOrderAction.ACTUALIZACION);
                }
                ordenMessage.setUidPago(ordenEntityMod.getUidPago());
                ordenMessage.setCodigoProducto(ordenEntityMod.getCodigoProducto());
                ordenMessage.setItems(items);
                kafkaProducerService.sendOrderToKafka(ordenMessage);
                ordenEntityMod.setEnviado(true);
            } catch (KafkaException e) {
                log.error("Error en envio de orden " , e);
            } finally {
                ordenRepository.persist(ordenEntityMod);
                for (OrdenItemEntity item:
                itemsEntity){
                    item.setOrden(ordenEntityMod);
                    ordenItemRepository.persist(item);
                }
            }
        }
        return orden;
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
        if(cliente.getCategoria().getNombre().equalsIgnoreCase("Platino")) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if(cliente.getCategoria().getNombre().equalsIgnoreCase("Dorado")) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if(cliente.getCategoria().getNombre().equalsIgnoreCase("Dorado") &&
                valorTotal.compareTo(MONTO_ESTABLECIDO) < 1) {
            respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;
        } else if(cliente.getCategoria().getNombre().equalsIgnoreCase("Plateado")) {
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
            clientesGETByIdRs = clienteService.validarCliente(codigoCliente);
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
        if(respuestaValidacion.getCodigo().equals(EnumRespuestaValidacion.RESPUESTA_EXITOSA.getCodigo())) {
            return respuestaValidacion.getUidPago();
        }
        return null;
    }

    /**
     * Realizar las reservas
     *
     * @param orden
     * @param cliente
     * @return lista encaso de exitoso
     */
    private Producto realizarReservas(Orden orden, Cliente cliente) {
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
        productosPSTRq.setProducto(producto);
        Response response = productoService.realizarReservas(productosPSTRq);
        if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
          producto = ((ProductosPSTRs)response.getEntity()).getProducto();
        }
        //producto.setId(new Random().nextLong());
        return producto;
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

        try {
            //Publish to kafka to persist in read table
            // only topic and message value are specified, round robin on destination partitions
            OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
            ordenMessage.setUidPago(ordenEntity.getUidPago());
            ordenMessage.setAccion(EnumOrderAction.CREACION);
            ordenMessage.setCodigoProducto(ordenEntity.getCodigoProducto());
            kafkaProducerService.sendOrderToKafka(ordenMessage);
            ordenEntity.setEnviado(true);
        } catch (KafkaException e) {
            log.error("Error en envio de orden " , e);
        } finally {
            ordenRepository.persist(ordenEntity);
        }

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

            try {
                //Publish to kafka to persist in read table
                // only topic and message value are specified, round robin on destination partitions
                OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
                ordenMessage.setUidPago(ordenEntityMod.getUidPago());
                ordenMessage.setAccion(EnumOrderAction.ACTUALIZACION);
                ordenMessage.setCodigoProducto(ordenEntityMod.getCodigoProducto());
                kafkaProducerService.sendOrderToKafka(ordenMessage);
                ordenEntityMod.setEnviado(true);
            } catch (KafkaException e) {
                log.error("Error en envio de orden " , e);
            } finally {
                ordenRepository.persist(ordenEntityMod);
                //persistir items de nuevo
                List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
                for (OrdenItemEntity item :
                        items) {
                    OrdenItem ordenItem = OrdenItemHelper.ordenItemEntityToOrdenItem(item, null);
                    OrdenItemEntity itemdup = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem, null);
                    itemdup.setOrden(ordenEntityMod);
                    ordenItemRepository.persist(itemdup);
                }
            }
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

            try {
                //Publish to kafka to persist in read table
                // only topic and message value are specified, round robin on destination partitions
                OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(ordenResponse, null);
                ordenMessage.setUidPago(ordenEntityMod.getUidPago());
                ordenMessage.setAccion(EnumOrderAction.ACTUALIZACION);
                ordenMessage.setCodigoProducto(ordenEntityMod.getCodigoProducto());
                kafkaProducerService.sendOrderToKafka(ordenMessage);
                ordenEntityMod.setEnviado(true);
            } catch (KafkaException e) {
                log.error("Error en envio de orden " , e);
            } finally {
                ordenRepository.persist(ordenEntityMod);
                //persistir items de nuevo
                List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
                for (OrdenItemEntity item :
                        items) {
                    OrdenItem ordenItem = OrdenItemHelper.ordenItemEntityToOrdenItem(item, null);
                    OrdenItemEntity itemdup = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem, null);
                    itemdup.setOrden(ordenEntityMod);
                    ordenItemRepository.persist(itemdup);
                }
            }
        } else {
            throw new OrdenNotFoundException("Verifique el codigo de la orden");
        }

        return true;
    }

}
