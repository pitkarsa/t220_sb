package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.models.OrderDetails;
import com.example.demo.models.Orders;

public interface OrderDetailsRepository 
  extends JpaRepository<OrderDetails, Integer> {
//	@RestResource(path = "/order-details")
	public List<OrderDetails> findAllByOrders(Orders orders);
}

