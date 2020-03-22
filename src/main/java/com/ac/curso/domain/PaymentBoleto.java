package com.ac.curso.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ac.curso.domain.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;


@Entity
@JsonTypeName("paymentBoleto")
public class PaymentBoleto extends Payment{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dateVencimento;
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	public PaymentBoleto() {}


	public PaymentBoleto(Long id, StatusPayment status, Order order, Date dataPagamento, Date dataVencimento) {
		super(id, status, order);
		this.dataPagamento = dataPagamento;
		this.dateVencimento = dataVencimento;
		
	}


	public Date getDateVencimento() {
		return dateVencimento;
	}


	public void setDateVencimento(Date dateVencimento) {
		this.dateVencimento = dateVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
	
	
}
