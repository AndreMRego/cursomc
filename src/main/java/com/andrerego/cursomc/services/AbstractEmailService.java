package com.andrerego.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.andrerego.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	//pegar valor do aplication.properties
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		//destinatario
		sm.setTo(obj.getCliente().getEmail());
		
		sm.setFrom(sender);
		//Asssunto
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		//Data do email
		sm.setSentDate(new Date(System.currentTimeMillis()));
		//corpo do email
		sm.setText(obj.toString());
		return sm;
	}

}
