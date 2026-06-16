package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.models.ProductSpecification;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public List<String> getAllCategories(){
		return repository.findDistinctCategories();
	}

	public List<Product> searchProductsByCriteria(String category, 
			String name, Integer maxPrice,
			String sortDirection) {
		
		Specification<Product> spec = Specification.unrestricted();
		// select * from product ;
		
		if(category != null) {
			spec = spec.and(ProductSpecification.hasCategory(category));
		}
		// where category="   ";
		if(name != null)// paneer
		{
			spec = spec.and(ProductSpecification.containingName(name));
		}
//		where name like '%paneer%'
		if(maxPrice != null) {
			spec = spec.and(ProductSpecification.priceLessThan(maxPrice));
		}
		// where price <= maxPrice
		
		Sort sort = Sort.unsorted();
		if(sortDirection != null) {
			Sort.Direction dir = sortDirection.equalsIgnoreCase("desc")?
					Sort.Direction.DESC : Sort.Direction.ASC;
			sort = Sort.by(new Sort.Order(dir, "price"));
		}
		// if (sortField!=null)
		
		List<Product> searchedProducts = repository.findAll(spec, sort);
		return searchedProducts;
	}

}
