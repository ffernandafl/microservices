package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.models.Product;

@FeignClient(name = "product-service", url = "http://localhost:8002")
@RequestMapping("/product")
public interface ProductFeignClient {

	@PostMapping
	public Product save(@RequestBody Product product);
	
	@GetMapping("/user/{userId}")
	public List<Product> getProducts(@PathVariable("userId") int userId);
	
}
