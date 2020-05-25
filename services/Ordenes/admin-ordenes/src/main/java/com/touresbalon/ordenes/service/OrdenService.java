package com.touresbalon.ordenes.service;

import com.touresbalon.ordenes.api.controller.OrdenesApiController;
import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.repository.OrdenItemRepository;
import com.touresbalon.ordenes.restclient.productos.ProductoService;
import com.touresbalon.ordenes.restclient.productos.model.*;
import com.touresbalon.ordenes.restclient.validacliente.ClienteService;
import com.touresbalon.ordenes.restclient.validacliente.model.ClientesGETByIdRs;
import com.touresbalon.ordenes.restclient.validatarjeta.model.*;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.exceptions.OrdenException;
import com.touresbalon.ordenes.helpers.OrdenHelper;
import com.touresbalon.ordenes.repository.OrdenRepository;
import com.touresbalon.ordenes.restclient.validatarjeta.TarjetaService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    /**
     * Realizar compra
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden realizarCompra(Orden orden) throws OrdenException {
        log.info("realizarCompra(Orden orden) ");
        //TODO: Llamar servicio de validar cliente
        Cliente cliente = validarCliente(orden.getCodigoCliente());

        //Llamar servicio de confirmacion de tarjeta
        if(validarTarjeta(orden, cliente)){

            OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
            ordenEntity.setEstado(Orden.EstadoEnum.EN_RESERVA);
            ordenEntity.setCodigo(null);
            ordenEntity.setFechaCreacion(LocalDateTime.now());
            ordenEntity.setFechaModificacion(LocalDateTime.now());

            ordenRepository.persist(ordenEntity);
            ordenEntity.getItems().forEach(item -> item.setOrden(ordenEntity));
            ordenEntity.getItems().forEach(ordenItemRepository::persist);

            ordenEntity.flush();
            //ordenEntity = ordenRepository.findById(ordenEntity.getId());

            //Llamar servicio de productos para reservar
            realizarReservas(orden, cliente);

            ordenEntity.setFechaPago(LocalDateTime.now());
            ordenEntity.setEstado(Orden.EstadoEnum.PAGADA);

            //TODO: Publish to kafka to persist in read table

            return OrdenHelper.ordenEntityToOrden(ordenEntity, orden);

        } else {
            throw new OrdenException("No paso la validacion de cupo de la tarjeta");
        }

    }

    /**
     * Realiza validacion del cliente
     * @param codigoCliente
     * @return Cliente si resulta exitoso
     */
    private Cliente validarCliente(Long codigoCliente) {
        log.info("validarCliente(Long codigoCliente) ");
        Cliente cliente ;
        ClientesGETByIdRs clientesGETByIdRs = null;
        try {
            //clientesGETByIdRs = clienteService.validarCliente(codigoCliente);
        } catch (Exception e) {
            log.error("validarCliente(Long codigoCliente) ", e);
        }
        if(clientesGETByIdRs != null){
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
     * @param orden
     * @param cliente
     * @return Exitoso si pasa la validacion
     */
    private boolean validarTarjeta (Orden orden, Cliente cliente) {
        log.info("validarTarjeta (Orden orden, Cliente cliente) ");
        CompraValidacion compraValidacion = new CompraValidacion();
        compraValidacion.setMonto(orden.getValorTotal());
        TipoIdentificacionValidacion tipoIdentificacion = new TipoIdentificacionValidacion
                (cliente.getTipoIdentificacion().getCodigo().toString(), cliente.getTipoIdentificacion().getNombre());
        compraValidacion.setCliente(new ClienteValidacion(cliente.getIdentificacion(),
                cliente.getNombres(), cliente.getApellidos(), cliente.getCelular(), tipoIdentificacion));
        compraValidacion.setTarjeta(new TarjetaValidacion(orden.getTarjeta().getNumero(), TarjetaValidacion.TipoEnum.fromValue(orden.getTarjeta().getTipo().name()) ));
        RespuestaValidacion respuestaValidacion = validaTarjetaService.validarTarjeta(compraValidacion);
        return respuestaValidacion.getCodigo().equals(EnumRespuestaValidacion.RESPUESTA_EXITOSA.getCodigo());
    }

    /**
     * Realizar las reservas
     * @param orden
     * @param cliente
     * @return lista encaso de exitoso
     */
    private List<ReservaExterna> realizarReservas(Orden orden, Cliente cliente){
        log.info("realizarReservas(Orden orden, Cliente cliente) ");
        List<ReservaExterna> reservas = null;
        ProductosPSTRq productosPSTRq = new ProductosPSTRq();
        Producto producto = new Producto();
        producto.setCliente(cliente);
        producto.setFechaCreacion(OffsetDateTime.now());
        producto.setPrecio(orden.getValorTotal().doubleValue());
        List<DetalleProducto> detalleProductosList = new ArrayList<>();
        if(orden.getHospedaje() != null && !orden.getHospedaje().isEmpty()) {
            for (Hospedaje hospedaje:
                    orden.getHospedaje()){
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setHospedaje(hospedaje);
                detalleProducto.setCuartosHospedaje(hospedaje.getCantidadCuartos());
                detalleProductosList.add(detalleProducto);
            }
        }
        if(orden.getEvento() != null && !orden.getEvento().isEmpty()) {
            producto.setDescripcion(orden.getEvento().get(0).getNombre());
            for (Evento evento:
                    orden.getEvento()){
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setEventos(evento);
                detalleProducto.setAsietosEvento(evento.getCantidad());
                detalleProductosList.add(detalleProducto);
            }
        }
        if(orden.getTransporte() != null && !orden.getTransporte().isEmpty()) {
            for (Transporte transporte:
                    orden.getTransporte()){
                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setTransporte(transporte);
                detalleProducto.setAsietosTransporte(transporte.getCantidadCupos());
                detalleProductosList.add(detalleProducto);
            }
        }
        producto.setDetalleProducto(detalleProductosList);
        productosPSTRq.setProducto(producto);
        Response response = productoService.realizarReservas(productosPSTRq);
        if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
            reservas = ((ProductosPSTRs)response.getEntity()).getReserva();
        }
        return reservas;
    }

    /**
     * Crea una orden
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden crearOrden(Orden orden) throws OrdenException {
        log.info("crearOrden(Orden orden) ");
        OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
        ordenEntity.setCodigo(null);
        ordenEntity.setFechaCreacion(LocalDateTime.now());
        ordenEntity.setFechaModificacion(LocalDateTime.now());

        ordenRepository.persist(ordenEntity);

        return OrdenHelper.ordenEntityToOrden(ordenEntity, orden);

    }

    /**
     *
     * @param orden
     * @param codigoOrden
     * @return
     * @throws OrdenException
     */
    public void actualizarOrden(Orden orden, Long codigoOrden) throws OrdenException {
        log.info("actualizarOrden(Orden orden, Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", codigoOrden).firstResultOptional();
        Orden ordenResponse = new Orden();
        if (ordenEntityOpt.isPresent()) {
            orden.setCodigo(codigoOrden);
            orden.setCodigoCliente(ordenEntityOpt.get().getCodigoCliente());
            orden.setFechaCreacion(ordenEntityOpt.get().getFechaCreacion());
            OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityOpt.get());
            ordenEntity.setFechaModificacion(LocalDateTime.now());
        } else {
            throw new OrdenException("Verifique el codigo de la orden");
        }
    }

    /**
     * BUsca una orden por su codigo
     *
     * @param codigoOrden
     * @return Orden encontrada
     */
    public Orden findOrdenByCodigoOrden(Long codigoOrden) {
        log.info("findOrdenByCodigoOrden(Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntity = Optional.ofNullable(ordenRepository.findOrdenByCodigoOrden(codigoOrden));
        Orden orden = new Orden();
        if (ordenEntity.isPresent()) {
            orden = OrdenHelper.ordenEntityToOrden(ordenEntity.get(),orden);
        } else {
            throw new OrdenException("No se encontro orden con este id");
        }
        return orden;
    }

    /**
     * Busca ordenes de acuerdo a los parametros de busqueda
     *
     * @param limit
     * @param codigoOrden
     * @param codigoCliente
     * @param fechaDesde
     * @param fechaHasta
     * @param estado
     * @return Lista de ordenes
     */
    public List<Orden> search(Integer limit, Long codigoOrden, Integer codigoCliente,
                              Date fechaDesde, Date fechaHasta, String estado) {
        log.info("search(...) ");
        List<Orden> ordenes;
        List<OrdenEntity> ordenesEntity = ordenRepository.search(limit, codigoOrden, codigoCliente,
                fechaDesde, fechaHasta, estado);
        if (!ordenesEntity.isEmpty()) {
            ordenes = new ArrayList<>();
            for (OrdenEntity ordenEntity :
                    ordenesEntity) {
                ordenes.add(OrdenHelper.ordenEntityToOrden(ordenEntity, null));
            }
        } else {
            throw new OrdenException("No se encontraron ordenes con los parametros buscados");
        }
        return ordenes;
    }

    /**
     *
     *
     * @param idOrden
     * @return
     * @throws OrdenException
     */
    public Orden buscarOrdenPorid(Long idOrden) throws OrdenException {
        log.info("buscarOrdenPorid(Long idOrden) ");
        Optional<OrdenEntity> ordenEntity = Optional.ofNullable(ordenRepository.findById(idOrden));
        Orden orden = new Orden();
        if (ordenEntity.isPresent()) {
            orden = OrdenHelper.ordenEntityToOrden(ordenEntity.get(),orden);
        } else {
            throw new OrdenException("No se encontro orden con este id");
        }
        return orden;
    }

    /**
     * Cancela una orden
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
            ordenEntityOpt.get().setEstado(Orden.EstadoEnum.CANCELADA);
            ordenRepository.persist(ordenEntityOpt.get());
        } else {
            throw new OrdenException("Verifique el codigo de la orden");
        }

        return true;
    }

}
