package com.ac.curso.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.ac.curso.domain.Order;

public abstract class
AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFormOrder(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFormOrder(Order obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
