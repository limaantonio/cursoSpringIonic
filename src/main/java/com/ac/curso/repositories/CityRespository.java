package com.ac.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.City;

public interface CityRespository extends JpaRepository<City, Long>{

	
	
}
