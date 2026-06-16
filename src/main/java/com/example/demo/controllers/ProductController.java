package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories(){
		List<String> allCategories = service.getAllCategories();
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	@GetMapping("/search-products")
	public ResponseEntity<?> searchProducts(
			@RequestParam(name="category", required = false) String category,
			@RequestParam(name="name", required = false) String name,
			@RequestParam(name="maxPrice", required = false) Integer maxPrice,
			@RequestParam(name="sort", required = false) String sortDirection			
			){		
		List<Product> searchedProducts = service.searchProductsByCriteria(category, name, maxPrice,sortDirection);	
		return new ResponseEntity<>(searchedProducts, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
