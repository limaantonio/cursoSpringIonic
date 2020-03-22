package com.ac.curso;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ac.curso.repositories.AddressRespository;
import com.ac.curso.repositories.CategoryRespository;
import com.ac.curso.repositories.CityRespository;
import com.ac.curso.repositories.ClientRespository;
import com.ac.curso.repositories.ItemOrderRespository;
import com.ac.curso.repositories.OrderRespository;
import com.ac.curso.repositories.PaymentRespository;
import com.ac.curso.repositories.ProductRespository;
import com.ac.curso.repositories.StateRespository;


@SpringBootApplication
public class CursoSpringIonicApplication implements CommandLineRunner{

	@Autowired
	CategoryRespository categoryRespository;
	
	@Autowired
	ProductRespository productRespository;
	
	@Autowired
	StateRespository stateRespository;
	
	@Autowired
	CityRespository cityRespository;
	
	@Autowired
	ClientRespository clientRespository;
	
	@Autowired
	AddressRespository addressRespository;
	
	@Autowired
	OrderRespository orderRespository;
	
	@Autowired
	PaymentRespository paymentRespository;
	
	@Autowired
	ItemOrderRespository itemOrderRespository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
