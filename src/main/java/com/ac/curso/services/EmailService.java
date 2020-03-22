package com.ac.curso.services;




import org.springframework.mail.SimpleMailMessage;

import com.ac.curso.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
}
