package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.OrderDetails;

public interface OrderDetailsRepository 
  extends JpaRepository<OrderDetails, Integer> {

}
