package com.ac.curso.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ac.curso.domain.PaymentBoleto;

@Service
public class BoletoService {

	public void preencherPaymentBoleto(PaymentBoleto pgto, Date instantePgto) {
		Calendar caledar = Calendar.getInstance();
		caledar.setTime(instantePgto);
		caledar.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDateVencimento(caledar.getTime());
	}
	
}
