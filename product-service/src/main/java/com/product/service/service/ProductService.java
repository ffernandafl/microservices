package com.product.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.service.entity.Product;
import com.product.service.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAll(){
		return productRepository.findAll();
	}
	
	public Product getProductById (int id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public Product save(Product product) {
		Product newProduct = productRepository.save(product);
		return newProduct;
	}
	
	public void deleteProduct (Integer id) {
		productRepository.deleteById(id);
	}
	
	public Product updateProduct (Product product) throws Exception
	{
		Optional<Product> updateProduct = productRepository.findById(product.getId());

	 	if (updateProduct.isPresent()) {
	 		
	 		Product newProduct = updateProduct.get();
	 		newProduct.setName(product.getName());
	 		newProduct.setColor(product.getColor());
	 		newProduct.setPrice(product.getPrice());

	 		newProduct = productRepository.save(newProduct);
	 	
	 	return newProduct;
		 	
		} else {
            throw new Exception("No product record exist for given id");
        }
		
	}
	
	
	public List<Product> byUserId(int userId){
		return productRepository.findByUserId(userId);
	}
}
