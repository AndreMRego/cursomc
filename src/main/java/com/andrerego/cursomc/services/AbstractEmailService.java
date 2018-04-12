package com.andrerego.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.andrerego.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	//pegar valor do aplication.properties
	@Value("${default.sender}")
	private String sender;
	
	//tranformar html em forma de string
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		//injetar obj pedido com o apelido de "pedido"
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
		
	}
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
		MimeMessage mm = prepareMimeMessageFromPedido(obj);
		sendHtmlEmail(mm);
		}catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
			MimeMessage mimeMessage= javaMailSender.createMimeMessage();
			MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
			mmh.setTo(obj.getCliente().getEmail());
			mmh.setFrom(sender);
			mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
			mmh.setSentDate(new Date(System.currentTimeMillis()));
			//pegar o corpo do email e true para afirmar que é html
			mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}

}
