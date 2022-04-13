package com.accesories.service.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accesories.service.entity.Accesories;
import com.accesories.service.service.AccesoriesService;


@RestController
@RequestMapping("/accesories")
public class AccesoriesController {

	@Autowired
	private AccesoriesService accesoriesService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Accesories>> listAccesories() {
		List<Accesories> accesories = accesoriesService.getAll();
		if(accesories.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accesories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Accesories> getAccesories(@PathVariable("id") int id) {
		Accesories accesories = accesoriesService.getAccesoriesById(id);
		if ( accesories == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(accesories);
	}
	
	@PostMapping
	public ResponseEntity<Accesories> saveAccesories (@RequestBody Accesories accesories) {
		Accesories newAccesories = accesoriesService.save(accesories);
		return ResponseEntity.ok(newAccesories);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAccesories(@PathVariable Integer id) {
		accesoriesService.deleteAccesories(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAccesories(@RequestBody Accesories accesories, @PathVariable Integer id) throws Exception{
		try {
			Accesories existAccesories = accesoriesService.updateAccesories(accesories);
			accesoriesService.save(accesories);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Accesories>> listAccesoriesByUserId (@PathVariable("userId") int id)  {
		List<Accesories> accesories = accesoriesService.byUserId(id);
		if(accesories.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accesories);
	}
	
}
