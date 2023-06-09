package br.com.devpro.msemail.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	private final ResourceLoader resourceLoader;
	
	public void sendSimpleMail(String to, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage(); 
		
		message .setTo(to);
		message .setSubject(subject);
		message .setText(body);

		mailSender.send(message);
		log.info("Enviando email...");

	}
	
	public void sendAttachmentMail(String destinatario, String titulo, String conteudo, String anexo) throws MessagingException{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

		mimeMessageHelper.setTo(destinatario);
		mimeMessageHelper.setSubject(titulo);
		mimeMessageHelper.setText(conteudo);

		Resource resource = resourceLoader.getResource(anexo);
		
		mimeMessageHelper.addAttachment(resource.getFilename(), resource);

		mailSender.send(mimeMessage);
		log.info("Enviando email...");
	}

}
