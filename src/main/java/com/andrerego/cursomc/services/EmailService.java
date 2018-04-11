package com.andrerego.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.andrerego.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
