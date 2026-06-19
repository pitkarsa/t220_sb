package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.models.OrderProjection;
import com.example.demo.models.Orders;

@RepositoryRestResource(excerptProjection = OrderProjection.class)
public interface OrdersRepository extends JpaRepository<Orders, String>{

}
