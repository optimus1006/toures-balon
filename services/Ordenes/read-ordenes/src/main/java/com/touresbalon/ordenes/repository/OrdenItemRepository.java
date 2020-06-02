package com.touresbalon.ordenes.repository;

import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class OrdenItemRepository implements PanacheRepository<OrdenItemEntity> {

    public List<OrdenItemEntity> findByOrden(OrdenEntity ordenEntity) {
        // create a query
        PanacheQuery<OrdenItemEntity> items = OrdenItemEntity.find("orden", ordenEntity);
        return items.list();
    }

    public Optional<OrdenItemEntity> findByOrdenAndCodigo(OrdenEntity orden, Long codigo){
        Map<String, Object> params = new HashMap<>();
        params.put("codigo", codigo);
        params.put("orden", orden);
        ;
        return OrdenItemEntity.find("codigo = :codigo and orden = :orden", params).firstResultOptional();
    }
}
