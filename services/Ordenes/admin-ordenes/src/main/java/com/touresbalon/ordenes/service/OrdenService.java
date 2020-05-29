package com.touresbalon.ordenes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import com.touresbalon.ordenes.exceptions.OrdenException;
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
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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
import java.time.OffsetDateTime;
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

    /**
     * Realizar compra
     *
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden realizarCompra(Orden orden) throws OrdenException {
        log.info("realizarCompra(Orden orden) ");
        //TODO: Llamar servicio de validar cliente
        Cliente cliente = consultarCliente(orden.getCodigoCliente());

        //Llamar servicio de confirmacion de tarjeta
        if (validarTarjeta(orden, cliente)) {
            EnumValidacionCliente validacionCliente = validarCliente(cliente);
            if (!validacionCliente.equals(EnumValidacionCliente.ENUM_VALIDACION_RECHAZADA)) {
                orden.setEstado(Orden.EstadoEnum.EN_RESERVA);
                orden.setCodigo(getCodigo());
                orden.setFechaCreacion(LocalDateTime.now());
                orden.setFechaModificacion(LocalDateTime.now());

                OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
                ordenRepository.persist(ordenEntity);

                List<OrdenItem> items = null;
                if (validacionCliente.equals(EnumValidacionCliente.ENUM_VALIDACION_EXITOSA)) {
                    //Llamar servicio de productos para reservar
                    Producto producto = realizarReservas(orden, cliente);
                    OrdenEntity ordenEntityMod = new OrdenEntity();

                    if (producto != null) {
                        items = new ArrayList<>();
                        List<OrdenItemEntity> itemList = new ArrayList<>();
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
                            OrdenItemEntity item = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem
                                    , null);
                            item.setOrden(ordenEntity);
                            ordenItemRepository.persist(item);
                            itemList.add(item);
                            items.add(ordenItem);
                        }
                        ordenEntityMod.setItems(itemList);
                    }

                    ordenEntityMod.setCodigoProducto(producto.getId());
                    ordenEntityMod.setFechaPago(LocalDateTime.now());
                    orden.setEstado(Orden.EstadoEnum.PAGADA);
                    ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityMod);
                    ordenRepository.persist(ordenEntityMod);
                }

                //TODO: Publish to kafka to persist in read table
                // only topic and message value are specified, round robin on destination partitions
                // sen Item list
                OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
                ordenMessage.setAccion(EnumOrderAction.PAGO);
                ordenMessage.setCodigoProducto(ordenEntity.getCodigoProducto());
                ordenMessage.setItems(items);
                kafkaProducerService.sendOrderToKafka(ordenMessage);
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
     * Realiza validacion del cliente
     *
     * @param cliente
     * @return 1 si resulta exitoso, 0 si no es exitoso y 2 si queda en revision
     */
    private EnumValidacionCliente validarCliente(Cliente cliente) {
        log.info("validarCliente(Cliente cliente) ");
        EnumValidacionCliente respuesta = EnumValidacionCliente.ENUM_VALIDACION_EXITOSA;

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
            tipoIdentificacion.setCodigo(new BigDecimal(10));
            tipoIdentificacion.setNombre("CC");
            cliente.setTipoIdentificacion(tipoIdentificacion);
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
    private boolean validarTarjeta(Orden orden, Cliente cliente) {
        log.info("validarTarjeta (Orden orden, Cliente cliente) ");
        CompraValidacion compraValidacion = new CompraValidacion();
        compraValidacion.setMonto(orden.getValorTotal());
        TipoIdentificacionValidacion tipoIdentificacion = new TipoIdentificacionValidacion
                (cliente.getTipoIdentificacion().getCodigo().toString(), cliente.getTipoIdentificacion().getNombre());
        compraValidacion.setCliente(new ClienteValidacion(cliente.getIdentificacion(),
                cliente.getNombres(), cliente.getApellidos(), cliente.getCelular(), tipoIdentificacion));
        compraValidacion.setTarjeta(new TarjetaValidacion(orden.getTarjeta().getNumero(), TarjetaValidacion.TipoEnum.fromValue(orden.getTarjeta().getTipo().name())));
        RespuestaValidacion respuestaValidacion = validaTarjetaService.validarTarjeta(compraValidacion);
        return respuestaValidacion.getCodigo().equals(EnumRespuestaValidacion.RESPUESTA_EXITOSA.getCodigo());
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

        ordenRepository.persist(ordenEntity);
        orden = OrdenHelper.ordenEntityToOrden(ordenEntity, orden);
        //Publish to kafka to persist in read table
        // only topic and message value are specified, round robin on destination partitions
        OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
        ordenMessage.setAccion(EnumOrderAction.CREACION);
        ordenMessage.setCodigoProducto(ordenEntity.getCodigoProducto());
        kafkaProducerService.sendOrderToKafka(ordenMessage);

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
                find("codigo", codigoOrden).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            OrdenEntity ordenEntity = ordenEntityOpt.get();
            orden.setFechaModificacion(LocalDateTime.now());
            orden.setCodigo(codigoOrden);
            orden.setCodigoCliente(ordenEntityOpt.get().getCodigoCliente());
            orden.setFechaCreacion(ordenEntityOpt.get().getFechaCreacion());
            orden.setFechaModificacion(LocalDateTime.now());
            OrdenEntity ordenEntityMod = OrdenHelper.ordenToOrdenEntity(orden, null);
            ordenRepository.persist(ordenEntityMod);
            //persistir items de nuevo
            List<OrdenItem> itemList = new ArrayList<>();
            List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
            for (OrdenItemEntity item :
                    items) {
                OrdenItem ordenItem = OrdenItemHelper.ordenItemEntityToOrdenItem(item, null);
                OrdenItemEntity itemdup = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem, null);
                em.persist(itemdup);
                itemList.add(ordenItem);
            }
            //Publish to kafka to persist in read table
            // only topic and message value are specified, round robin on destination partitions
            OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(orden, null);
            ordenMessage.setAccion(EnumOrderAction.ACTUALIZACION);
            ordenMessage.setCodigoProducto(ordenEntity.getCodigoProducto());
            ordenMessage.setItems(itemList);
            kafkaProducerService.sendOrderToKafka(ordenMessage);
        } else {
            throw new OrdenException("Verifique el codigo de la orden");
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
                find("codigo", codigoOrden).firstResultOptional();
        Orden ordenResponse = new Orden();
        if (ordenEntityOpt.isPresent()) {
            OrdenEntity ordenEntity = ordenEntityOpt.get();
            ordenResponse = OrdenHelper.ordenEntityToOrden(ordenEntity, ordenResponse);
            ordenResponse.setFechaModificacion(LocalDateTime.now());
            ordenResponse.setEstado(Orden.EstadoEnum.CANCELADA);
            OrdenEntity ordenEntityMod = OrdenHelper.ordenToOrdenEntity(ordenResponse, null);
            ordenRepository.persist(ordenEntityMod);
            //persistir items de nuevo
            List<OrdenItem> itemList = new ArrayList<>();
            List<OrdenItemEntity> items = ordenItemRepository.findByOrden(ordenEntityOpt.get());
            for (OrdenItemEntity item :
                    items) {
                OrdenItem ordenItem = OrdenItemHelper.ordenItemEntityToOrdenItem(item, null);
                OrdenItemEntity itemdup = OrdenItemHelper.ordenItemToOrdenItemEntity(ordenItem, null);
                em.persist(itemdup);
                itemList.add(ordenItem);
            }
            //Publish to kafka to persist in read table
            // only topic and message value are specified, round robin on destination partitions
            OrdenMessage ordenMessage = OrdenHelper.ordenToOrdenMessage(ordenResponse, null);
            ordenMessage.setAccion(EnumOrderAction.ACTUALIZACION);
            ordenMessage.setCodigoProducto(ordenEntity.getCodigoProducto());
            ordenMessage.setItems(itemList);
            kafkaProducerService.sendOrderToKafka(ordenMessage);
        } else {
            throw new OrdenException("Verifique el codigo de la orden");
        }

        return true;
    }

}
