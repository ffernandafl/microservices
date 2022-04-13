package com.accesories.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesories.service.entity.Accesories;
import com.accesories.service.repository.AccesoriesRepository;


@Service
public class AccesoriesService {


	@Autowired
	private AccesoriesRepository accesoriesRepository;
	
	public List<Accesories> getAll(){
		return accesoriesRepository.findAll();
	}
	
	public Accesories getAccesoriesById (int id) {
		return accesoriesRepository.findById(id).orElse(null);
	}
	
	public Accesories save(Accesories accesories) {
		Accesories newAccesories = accesoriesRepository.save(accesories);
		return newAccesories;
	}
	
	public void deleteAccesories (Integer id) {
		accesoriesRepository.deleteById(id);
	}
	
	public Accesories updateAccesories (Accesories accesories) throws Exception
	{
		Optional<Accesories> updateAccesories = accesoriesRepository.findById(accesories.getId());

	 	if (updateAccesories.isPresent()) {
	 		
	 		Accesories newAccesories = updateAccesories.get();
	 		newAccesories.setName(accesories.getName());
	 		newAccesories.setColor(accesories.getColor());
	 		newAccesories.setPrice(accesories.getPrice());

	 		newAccesories = accesoriesRepository.save(newAccesories);
	 	
	 	return newAccesories;
		 	
		} else {
            throw new Exception("No accesories record exist for given id");
        }
		
	}
	
	
	public List<Accesories> byUserId(int userId){
		return accesoriesRepository.findByUserId(userId);
	}
	
}
