package br.com.devpro.msemail.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devpro.msemail.client.MacrosXtremeClient;
import br.com.devpro.msemail.dto.EmailDTO;
import br.com.devpro.msemail.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

	private final EmailService emailService;
	
	private final MacrosXtremeClient macrosXtremeClient;

	@PostMapping
	public ResponseEntity<String> enviar(@RequestBody EmailDTO email) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		String dataHoraFormatada = dataHoraAtual.format(formatador);

		email.setDataEnvio(dataHoraFormatada);

		try {
			emailService.sendSimpleMail(email.getDestinatario(), email.getTituloEmail(), email.getConteudo());
			macrosXtremeClient.salvarHistoricoEmail(email);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) {
			log.error("Erro: {}", e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro 500: Internal Server Error");

	}

}