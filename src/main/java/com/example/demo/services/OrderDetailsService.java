package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OrderDetails;
import com.example.demo.models.Orders;
import com.example.demo.repositories.OrderDetailsRepository;
import com.example.demo.repositories.OrdersRepository;

@Service
public class OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepository repository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<OrderDetails> getOrderDetails(String orderId){
		
		Orders orders = ordersRepository.findById(orderId).get();
		return repository.findAllByOrders(orders);
	}
	
}
