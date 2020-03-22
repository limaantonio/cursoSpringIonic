package com.ac.curso.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.ac.curso.domain.Address;
import com.ac.curso.domain.Category;
import com.ac.curso.domain.City;
import com.ac.curso.domain.Client;
import com.ac.curso.domain.ItemOrder;
import com.ac.curso.domain.Order;
import com.ac.curso.domain.Payment;
import com.ac.curso.domain.PaymentBoleto;
import com.ac.curso.domain.PaymentCartao;
import com.ac.curso.domain.Product;
import com.ac.curso.domain.State;
import com.ac.curso.domain.enums.StatusPayment;
import com.ac.curso.domain.enums.TipoCliente;
import com.ac.curso.repositories.AddressRespository;
import com.ac.curso.repositories.CategoryRespository;
import com.ac.curso.repositories.CityRespository;
import com.ac.curso.repositories.ClientRespository;
import com.ac.curso.repositories.ItemOrderRespository;
import com.ac.curso.repositories.OrderRespository;
import com.ac.curso.repositories.PaymentRespository;
import com.ac.curso.repositories.ProductRespository;
import com.ac.curso.repositories.StateRespository;

public class DbService {
	
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

	public void instantiateTestDatabase() throws ParseException {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Material Escolar");
		Category cat4 = new Category(null, "Cama, mesa e banho");
		Category cat5 = new Category(null, "Eletrodomesticos");
		
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Impressora", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat1);
		
		categoryRespository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		productRespository.saveAll(Arrays.asList(p1, p2, p3));
		
		State e1 = new State(null, "Minas Gerais");
		State e2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", e1);
		City c2 = new City(null, "São Paulo", e2);
		City c3 = new City(null, "Campinas", e2);
		
		e1.getCities().addAll(Arrays.asList(c1));
		e2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRespository.saveAll(Arrays.asList(e1, e2));
		cityRespository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "504545454", TipoCliente.PESSOAFISICA.getCod());
		
		cli1.getTelefones().addAll(Arrays.asList("9494934", "498348394"));
		
		Address end1 = new Address(null, "Rua da Flores", "300", "Apto 203", "Jardim", "43434344", cli1, c1);
		Address end2 = new Address(null, "Av das Flores", "300", "Apto 23", "Jardim Claro", "23334344", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(end1, end2));
		
		clientRespository.saveAll(Arrays.asList(cli1));
		addressRespository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Order ped2 = new Order(null, sdf.parse("10/10/2010 10:30"), cli1, end2);
		
		Payment pgto1 = new PaymentCartao(null, StatusPayment.QUITADO, ped1, 6);
		ped1.setPayment(pgto1);
		
		PaymentBoleto pto2 = new PaymentBoleto(null, StatusPayment.PENDETE, ped2, sdf.parse("30/10/2017 00:00"), null);
		ped2.setPayment(pto2);
		
		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));
		
		orderRespository.saveAll(Arrays.asList(ped1, ped2));
		
		paymentRespository.saveAll(Arrays.asList(pto2, pgto1));
		
		ItemOrder ip1 = new ItemOrder(ped1, p1, 0.0, 1, 2000.0);
		ItemOrder ip2 = new ItemOrder(ped1, p3, 0.0, 2, 80.0);
		ItemOrder ip3 = new ItemOrder(ped2, p2, 100.0, 1, 800.0);
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemOrderRespository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
