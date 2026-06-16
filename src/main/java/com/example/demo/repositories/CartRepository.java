package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.models.Cart;
import com.example.demo.models.CartProjection;

@RepositoryRestResource(excerptProjection = CartProjection.class)
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
