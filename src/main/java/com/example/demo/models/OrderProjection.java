package com.example.demo.models;

import java.time.Instant;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Orders.class})
public interface OrderProjection {
	public String getOrderId() ;
	public String getPaymentId();
	public Instant getOrderDate() ;
	//public User getUser();
	public int getTotalBill();
}
