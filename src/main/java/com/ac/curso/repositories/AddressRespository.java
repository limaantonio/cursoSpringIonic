package com.ac.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.Address;

public interface AddressRespository extends JpaRepository<Address, Long>{

	
	
}
