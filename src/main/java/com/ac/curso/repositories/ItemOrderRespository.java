package com.ac.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.ItemOrder;

public interface ItemOrderRespository extends JpaRepository<ItemOrder, Long>{

	
	
}
