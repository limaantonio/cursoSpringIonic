package com.ac.curso.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.Client;

public interface ClientRespository extends JpaRepository<Client, Long>{

	@Transactional
	Client findByEmail(String email);
	
}
