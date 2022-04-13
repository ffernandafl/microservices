package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.models.Accesories;

@FeignClient(name = "accesories-service", url = "http:localhost:8003")
@RequestMapping("/accesories")
public interface AccesoriesFeignClient {

	@PostMapping()
	public Accesories save(@RequestBody Accesories accesories); 
	
	@GetMapping("/user/{userId}")
	public List<Accesories> getAccesories(@PathVariable("userId") int userId);
	
}
