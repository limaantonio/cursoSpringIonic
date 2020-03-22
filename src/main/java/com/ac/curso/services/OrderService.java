package com.ac.curso.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.curso.domain.ItemOrder;
import com.ac.curso.domain.Order;
import com.ac.curso.domain.PaymentBoleto;
import com.ac.curso.domain.enums.StatusPayment;
import com.ac.curso.repositories.ItemOrderRespository;
import com.ac.curso.repositories.OrderRespository;
import com.ac.curso.repositories.PaymentRespository;
import com.ac.curso.service.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderRespository repository;
	
	@Autowired
	private PaymentRespository paymentRepository;
	
	@Autowired
	private ItemOrderRespository itemOrderRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProductService productService;
	
	
	
	public List<Order> findAll (){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPayment().setEstado(StatusPayment.PENDETE);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pgto = (PaymentBoleto) obj.getPayment();
			boletoService.preencherPaymentBoleto(pgto, obj.getInstante());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemOrder ip : obj.getItens()) {
			ip.setDesconto(0.0);
			
			
			ip.setPrice(productService.findById(ip.getProduct().getId()).getPrice());
			ip.setOrde(obj);
		}
		itemOrderRepository.saveAll(obj.getItens());
		return obj;
	}
	
}
