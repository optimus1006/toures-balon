package com.touresbalon.ordenes.repository;

import com.touresbalon.ordenes.entities.OrdenItemEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrdenItemRepository implements PanacheRepository<OrdenItemEntity> {

}
