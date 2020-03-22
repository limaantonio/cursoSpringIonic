package com.ac.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.curso.domain.Payment;

public interface PaymentRespository extends JpaRepository<Payment, Long>{

	
	
}
