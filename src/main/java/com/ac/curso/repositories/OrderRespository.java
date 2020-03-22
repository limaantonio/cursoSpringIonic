package com.ac.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.Order;

public interface OrderRespository extends JpaRepository<Order, Long>{

	
	
}
