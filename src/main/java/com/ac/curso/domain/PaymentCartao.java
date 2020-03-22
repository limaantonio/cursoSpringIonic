package com.ac.curso.domain;

import javax.persistence.Entity;

import com.ac.curso.domain.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;


@Entity
@JsonTypeName("paymentCartao")
public class PaymentCartao extends Payment{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroParcela;

	public PaymentCartao() {
		
	}

	public PaymentCartao(Long id, StatusPayment status, Order order, Integer numerParcela) {
		super(id, status, order);
		this.numeroParcela = numerParcela;
		
	}


	public Integer getNumeroParcela() {
		return numeroParcela;
	}


	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	
	

}
