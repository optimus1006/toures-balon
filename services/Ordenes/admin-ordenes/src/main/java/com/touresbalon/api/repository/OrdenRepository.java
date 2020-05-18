package com.touresbalon.api.repository;

import com.touresbalon.api.entities.OrdenEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OrdenRepository implements PanacheRepository<OrdenEntity> {

	/**
	 * BUsca una orden por su codigo
	 * @param codigoOrden
	 * @return Orden encontrada
	 */
	public OrdenEntity findOrdenByCodigoOrden(Long codigoOrden) {
		// create a query
		PanacheQuery<OrdenEntity> ordenes = OrdenEntity.find("codigo", codigoOrden);
		return ordenes.firstResult();
	}

	/**
	 * Busca ordenes de acuerdo a los parametros de busqueda
	 * @param limit
	 * @param codigoOrden
	 * @param codigoCliente
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param estado
	 * @return Lista de ordenes
	 */
	public List<OrdenEntity> search(Integer limit, Long codigoOrden, Integer codigoCliente, Date fechaDesde,
									Date fechaHasta, String estado) {
		limit = limit != null ? limit : 0;
		StringBuilder query = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		int index = 1;
		if(codigoOrden != null) {
			query.append("codigo = :codigo");
			params.put("codigo", codigoOrden);
			index++;
		}
		if(codigoCliente != null) {
			if(index > 1) {
				query.append(" and ");
			}
			query.append("codigoCliente = :codigoCliente");
			params.put("codigoCliente", codigoCliente);
			index++;
		}
		if(fechaDesde != null) {
			if(index > 1) {
				query.append(" and ");
			}
			query.append("fechaCreacion >= :fechaDesde");
			params.put("fechaDesde", fechaDesde);
			index++;
			if(fechaHasta != null) {
				query.append(" and ");
				query.append("fechaHasta <= :fechaHasta");
				params.put("fechaHasta", fechaHasta);
			}
		}
		if(estado != null) {
			if(index > 1) {
				query.append(" and ");
			}
			query.append("estado = :estado");
			params.put("estado", estado);
			index++;
		}

		// create a query
		PanacheQuery<OrdenEntity> ordenes = null;
		if(index > 1){
			ordenes = OrdenEntity.find(query.toString(), Sort.by("fechaCreacion").descending(), params);
		} else {
			ordenes = OrdenEntity.findAll(Sort.by("fechaCreacion").descending());
		}


		// make it use pages of 25 entries at a time
		if(limit > 0) {
			ordenes.page(Page.ofSize(limit));
		}

		return ordenes.list();
	}
}
