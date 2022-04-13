package com.accesories.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accesories.service.entity.Accesories;


public interface AccesoriesRepository extends JpaRepository<Accesories, Integer>{

	List<Accesories> findByUserId(int userId);
}
