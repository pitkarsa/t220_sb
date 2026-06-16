package com.example.demo.models;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Product.class})
public interface ProductProjection {
	public int getId();
	public String getName() ;
	public String getCategory();
	public String getDescription() ;
	public String getImageUrl() ;
	public int getPrice() ;
}
