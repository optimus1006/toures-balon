package com.touresbalon.ordenes.service;

import com.touresbalon.ordenes.api.model.*;
import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import com.touresbalon.ordenes.exceptions.OrdenException;
import com.touresbalon.ordenes.helpers.OrdenHelper;
import com.touresbalon.ordenes.helpers.OrdenItemHelper;
import com.touresbalon.ordenes.kafka.OrdenMessage;
import com.touresbalon.ordenes.repository.OrdenItemRepository;
import com.touresbalon.ordenes.repository.OrdenRepository;
import com.touresbalon.ordenes.restclient.productos.ProductoService;
import com.touresbalon.ordenes.restclient.productos.model.DetalleProducto;
import com.touresbalon.ordenes.restclient.productos.model.Producto;
import com.touresbalon.ordenes.restclient.productos.model.ProductosPSTRs;
import com.touresbalon.ordenes.util.EnumTipoProducto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
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
    ProductoService productoService;

    /**
     * Realizar compra
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden realizarCompra(OrdenMessage orden) throws OrdenException {
        log.info("realizarCompra(Orden orden) ");

        OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
        ordenEntity.setCodigoProducto(orden.getCodigoProducto());
        ordenEntity.setUidPago(orden.getUidPago());
        ordenRepository.persist(ordenEntity);
        if(orden.getItems() != null && !orden.getItems().isEmpty()) {
            List<OrdenItemEntity> itemList = new ArrayList<>();
            for (OrdenItem item :
                    orden.getItems()) {
                itemList.add(OrdenItemHelper.ordenItemToOrdenItemEntity(item, null));
            }
            ordenEntity.setItems(itemList);
            ordenEntity.getItems().forEach(item -> item.setOrden(ordenEntity));
            ordenEntity.getItems().forEach(ordenItemRepository::persist);
        }

        return OrdenHelper.ordenEntityToOrden(ordenEntity, orden);

    }

    /**
     * Crea una orden
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden crearOrden(OrdenMessage orden) throws OrdenException {
        log.info("crearOrden(Orden orden) ");
        OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, null);
        ordenEntity.setCodigoProducto(orden.getCodigoProducto());
        ordenEntity.setUidPago(orden.getUidPago());

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
    public void actualizarOrden(OrdenMessage orden, Long codigoOrden) throws OrdenException {
        log.info("actualizarOrden(Orden orden, Long codigoOrden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", codigoOrden).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            orden.setCodigo(codigoOrden);
            orden.setCodigoCliente(ordenEntityOpt.get().getCodigoCliente());
            orden.setFechaCreacion(ordenEntityOpt.get().getFechaCreacion());
            OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityOpt.get());
            ordenEntity.setFechaModificacion(LocalDateTime.now());
            ordenEntity.setCodigoProducto(orden.getCodigoProducto());
            ordenEntity.setFechaModificacion(LocalDateTime.now());
            ordenEntity.setUidPago(ordenEntityOpt.get().getUidPago());
            em.merge(ordenEntity);

            if(orden.getItems() != null && !orden.getItems().isEmpty()) {
                List<OrdenItemEntity> itemList = new ArrayList<>();
                for (OrdenItem item :
                        orden.getItems()) {
                    Optional<OrdenItemEntity> itemF = ordenItemRepository.findByOrdenAndCodigo(ordenEntity, item.getCodigo());
                    if (itemF.isPresent()) {
                        itemF.get().setCodigoReserva(item.getCodigoReserva());
                        em.merge(itemF.get());
                    } else {
                        OrdenItemEntity ordenItem = OrdenItemHelper.ordenItemToOrdenItemEntity(item, null);
                        ordenItem.setOrden(ordenEntity);
                        ordenItem.persist();
                    }
                }

            }
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
            List<OrdenItemEntity> itemsEntity = ordenItemRepository.findByOrden(ordenEntity.get());
            if(itemsEntity != null && !itemsEntity.isEmpty()) {
                //TODO: Consultar productos
                Producto producto= new Producto();
                /*Response responseProducto = productoService.consultarProductoPorId(ordenEntity.get().getCodigoProducto());
                if(responseProducto.getStatus() == Response.Status.OK.getStatusCode()){
                  producto = ((ProductosPSTRs)response.getEntity()).getProducto();
                }*/
                List<Transporte> transportes = new ArrayList<>();
                List<Hospedaje> hospedajes = new ArrayList<>();
                List<Evento> eventos = new ArrayList<>();
                for (DetalleProducto detalleProducto :
                        producto.getDetalleProducto()) {
                    if (detalleProducto.getEventos() != null) {
                        eventos.add(detalleProducto.getEventos());
                    } else if (detalleProducto.getHospedaje() != null) {
                        hospedajes.add(detalleProducto.getHospedaje());
                    } else if (detalleProducto.getTransporte() != null) {
                        transportes.add(detalleProducto.getTransporte());
                    }
                }
                orden.setHospedaje(hospedajes);
                orden.setEvento(eventos);
                orden.setTransporte(transportes);
            }
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
            List<OrdenItemEntity> itemsEntity = ordenItemRepository.findByOrden(ordenEntity.get());
            if(itemsEntity != null && !itemsEntity.isEmpty()) {
                //TODO: Consultar productos
                Producto producto= new Producto();
                Response responseProducto = productoService.consultarProductoPorId(ordenEntity.get().getCodigoProducto());
                if(responseProducto.getStatus() == Response.Status.OK.getStatusCode()){
                  producto = ((ProductosPSTRs)responseProducto.getEntity()).getProducto();
                }
                List<Transporte> transportes = new ArrayList<>();
                List<Hospedaje> hospedajes = new ArrayList<>();
                List<Evento> eventos = new ArrayList<>();
                for (DetalleProducto detalleProducto :
                        producto.getDetalleProducto()) {
                    if (detalleProducto.getEventos() != null) {
                        eventos.add(detalleProducto.getEventos());
                    } else if (detalleProducto.getHospedaje() != null) {
                        hospedajes.add(detalleProducto.getHospedaje());
                    } else if (detalleProducto.getTransporte() != null) {
                        transportes.add(detalleProducto.getTransporte());
                    }
                }
                orden.setHospedaje(hospedajes);
                orden.setEvento(eventos);
                orden.setTransporte(transportes);
            }
        } else {
            throw new OrdenException("No se encontro orden con este id");
        }
        return orden;
    }

    /**
     *
     * @param orden
     * @param codigoOrden
     * @return
     * @throws OrdenException
     */
    public void agregarItem(OrdenMessage orden) throws OrdenException {
        log.info("agregarItem(OrdenMessage orden) ");
        Optional<OrdenEntity> ordenEntityOpt = ordenRepository.
                find("codigo", orden.getCodigo()).firstResultOptional();
        if (ordenEntityOpt.isPresent()) {
            if(ordenEntityOpt.get().getCodigoProducto() == null) {
                orden.setCodigo(orden.getCodigo());
                orden.setCodigoCliente(ordenEntityOpt.get().getCodigoCliente());
                orden.setFechaCreacion(ordenEntityOpt.get().getFechaCreacion());
                OrdenEntity ordenEntity = OrdenHelper.ordenToOrdenEntity(orden, ordenEntityOpt.get());
                ordenEntity.setCodigoProducto(orden.getCodigoProducto());
                ordenEntity.setFechaModificacion(LocalDateTime.now());
                ordenEntity.setUidPago(ordenEntityOpt.get().getUidPago());
                em.merge(ordenEntity);
            }

            if(orden.getItems() != null && !orden.getItems().isEmpty()) {
                List<OrdenItemEntity> itemList = new ArrayList<>();
                for (OrdenItem item :
                        orden.getItems()) {
                    Optional<OrdenItemEntity> itemF = ordenItemRepository.
                            findByOrdenAndCodigo(ordenEntityOpt.get(), item.getCodigo());
                    if (itemF.isPresent()) {
                        itemF.get().setCodigoReserva(item.getCodigoReserva());
                        em.merge(itemF.get());
                    } else {
                        OrdenItemEntity ordenItem = OrdenItemHelper.ordenItemToOrdenItemEntity(item, null);
                        ordenItem.setOrden(ordenEntityOpt.get());
                        ordenItem.persist();
                    }
                }

            }
        } else {
            throw new OrdenException("Verifique el codigo de la orden");
        }
    }

}
