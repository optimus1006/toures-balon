package com.touresbalon.ordenes.repository;

import com.touresbalon.ordenes.entities.OrdenEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

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

}
