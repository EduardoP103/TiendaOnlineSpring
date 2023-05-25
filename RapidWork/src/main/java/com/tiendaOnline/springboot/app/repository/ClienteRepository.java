package com.tiendaOnline.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.tiendaOnline.springboot.app.models.entity.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente,Integer>{

}
