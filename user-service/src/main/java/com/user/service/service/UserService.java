package com.user.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.User;
import com.user.service.feignclients.AccesoriesFeignClient;
import com.user.service.feignclients.ProductFeignClient;
import com.user.service.models.Accesories;
import com.user.service.models.Product;
import com.user.service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private AccesoriesFeignClient accesoriesFeignClient;
	
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getUserById (int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
	public List<Product> getProducts(int userId) {
		List<Product> products = restTemplate.getForObject("http://localhost:8002/product/user/" + userId, List.class);
		return products;
	}
	
	public List<Accesories> getAccesories(int userId) {
		List<Accesories> accesories = restTemplate.getForObject("http://localhost:8003/accesories/user/" + userId, List.class);
		return accesories;
	}
	
	public Product saveProduct(int userId, Product product) {
		product.setUserId(userId);
		Product newProduct = productFeignClient.save(product);
		return newProduct;
	}
	
	public Accesories saveAccesories(int userId, Accesories accesories) {
		accesories.setUserId(userId);
		Accesories newAccesories = accesoriesFeignClient.save(accesories);
		return newAccesories;
	}
	
	public Map<String, Object> getUserAndProducts (int userId){
		Map<String, Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			result.put("Message", "User doesn't exist");
			return result;
		}
		result.put("User", user);
		List<Product> products = productFeignClient.getProducts(userId);
		if ( products.isEmpty()) {
			result.put("Products", "User doesn't have products");
		} else {
			result.put("Products", products);
		}
		
		List<Accesories> accesories = accesoriesFeignClient.getAccesories(userId);
		if (accesories.isEmpty()) {
			result.put("Accesories", "User doesn't have accesories");
		} else {
			result.put("Accesories", accesories);
		}
		
		return result;
	}
	
}
