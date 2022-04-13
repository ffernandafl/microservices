package com.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByUserId(int userId);
	
}
