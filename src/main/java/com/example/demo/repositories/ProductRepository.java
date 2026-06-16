package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.models.Product;
import com.example.demo.models.ProductProjection;

//@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(excerptProjection = ProductProjection.class)
public interface ProductRepository extends 
	JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>
{
	// localhost:8080/products  - returns projected data
	// localhost:8080/products/2?projection=productProjection  - returns the single product (projected)
	
	// custom query - JPQL : 
	// ensure that we are referring to classname: Product
	//and instance variable (category)  of the entity class
	@Query("select distinct p.category from Product p")
	public List<String> findDistinctCategories();
	
	
}








