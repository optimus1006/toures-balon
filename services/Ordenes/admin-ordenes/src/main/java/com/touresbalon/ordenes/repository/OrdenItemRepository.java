package com.touresbalon.ordenes.repository;

import com.touresbalon.ordenes.entities.OrdenEntity;
import com.touresbalon.ordenes.entities.OrdenItemEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OrdenItemRepository implements PanacheRepository<OrdenItemEntity> {


    public List<OrdenItemEntity> findByOrden(OrdenEntity orden){
        return list("orden", orden);
    }
}
