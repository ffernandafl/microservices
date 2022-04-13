package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.models.Accesories;
import com.user.service.models.Product;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> listUser() {
		List<User> users = userService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if ( user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser (@RequestBody User user) {
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/product/{userId}")
	public ResponseEntity<List<Product>> listProducts(@PathVariable("userId") int id) {
		User user = userService.getUserById(id);
		if ( user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Product> products = userService.getProducts(id);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/accesories/{userId}")
	public ResponseEntity<List<Accesories>> listAccesories(@PathVariable("userId") int id) {
		User user = userService.getUserById(id);
		if ( user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Accesories> accesories = userService.getAccesories(id);
		return ResponseEntity.ok(accesories);
	}
	
	@PostMapping("/product/{userId}")
	public ResponseEntity<Product> saveProduct(@PathVariable("userId") int userId, @RequestBody Product product) {
		Product newProduct = userService.saveProduct(userId, product);
		return ResponseEntity.ok(newProduct);
	}
	
	@PostMapping("/product/{userId}")
	public ResponseEntity<Accesories> saveAccesories(@PathVariable("userId") int userId, @RequestBody Accesories accesories) {
		Accesories newAccesories = userService.saveAccesories(userId, accesories);
		return ResponseEntity.ok(newAccesories);
	}
	
	@GetMapping("all/{userId}")
	public ResponseEntity <Map<String, Object>> listAllProducts (@PathVariable("userId") int userId) {
		Map<String, Object> result = userService.getUserAndProducts(userId);
		return ResponseEntity.ok(result);
	}
	
}
