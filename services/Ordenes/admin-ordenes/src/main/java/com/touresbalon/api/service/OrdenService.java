package com.touresbalon.api.service;

import com.touresbalon.api.domain.Orden;
import com.touresbalon.api.entities.OrdenEntity;
import com.touresbalon.api.exceptions.OrdenException;
import com.touresbalon.api.helpers.OrdenHelper;
import com.touresbalon.api.repository.OrdenRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class OrdenService {

    @Inject
    OrdenRepository ordenRepository;

    /**
     * Crea una orden
     * @param orden
     * @return Codigo de orden creada
     * @throws OrdenException
     */
    public Orden crearOrden(Orden orden) throws OrdenException {
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
        List<Orden> ordenes;
        List<OrdenEntity> ordenesEntity = ordenRepository.search(limit, codigoOrden, codigoCliente,
                fechaDesde, fechaHasta, estado);
        if (ordenesEntity.isEmpty()) {
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
