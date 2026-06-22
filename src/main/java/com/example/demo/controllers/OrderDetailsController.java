package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.OrderDetails;
import com.example.demo.services.OrderDetailsService;

@RestController
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsService service;

	@GetMapping("/order-details/{orderId}")
	public ResponseEntity<?> getDetails(@PathVariable String orderId){
		List<OrderDetails> details = service.getOrderDetails(orderId);
		return new ResponseEntity<>(details,HttpStatus.OK );
	}
}
