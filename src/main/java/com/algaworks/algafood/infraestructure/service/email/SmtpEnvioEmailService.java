package com.algaworks.algafood.infraestructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
/**
 * Classe de estrutura de 
 * implementação do envio de E-mail
 * 
 * @author Leonardo
 *
 */
@Service
public class SmtpEnvioEmailService implements EnvioEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());
			helper.setText(mensagem.getCorpo(), true); //Texto em HTML
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}

}
