package com.product.service.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.entity.Product;
import com.product.service.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> listProduct() {
		List<Product> products = productService.getAll();
		if(products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		if ( product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> saveProduct (@RequestBody Product product) {
		Product newProduct = productService.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id) throws Exception{
		try {
			Product existProduct = productService.updateProduct(product);
			productService.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Product>> listProductsByUserId (@PathVariable("userId") int id)  {
		List<Product> products = productService.byUserId(id);
		if(products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	
}
