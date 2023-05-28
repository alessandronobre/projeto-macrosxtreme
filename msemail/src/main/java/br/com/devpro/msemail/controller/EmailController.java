package br.com.devpro.msemail.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devpro.msemail.dto.HistoricoEmailDTO;
import br.com.devpro.msemail.model.HistoricoEmail;
import br.com.devpro.msemail.service.EmailService;
import br.com.devpro.msemail.service.HistoricoEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private HistoricoEmailService historicoEmailService;

	@CrossOrigin(origins = "http://localhost:3002", allowedHeaders = "http://localhost:3002")
	@PostMapping
	public void enviar(@RequestBody HistoricoEmailDTO historicoEmailDTO) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		String dataHoraFormatada = dataHoraAtual.format(formatador);

		HistoricoEmailDTO historicoEmail = new HistoricoEmailDTO();
		historicoEmail.setUsuario(historicoEmailDTO.getUsuario());
		historicoEmail.setTituloEmail(historicoEmailDTO.getTituloEmail());
		historicoEmail.setConteudo(historicoEmailDTO.getConteudo());
		historicoEmail.setDataEnvio(dataHoraFormatada);
		historicoEmail.setDestinatario(historicoEmailDTO.getDestinatario());

		try {
//			emailService.sendSimpleMail("alessandronobre.ti@gmail.com", "Teste", "TESTE");

			historicoEmailService.salvarHistorico(historicoEmail);

		} catch (Exception e) {
			log.error("Erro: {}", e.getMessage());
		}

	}

	@GetMapping
	public void buscarHistoricoUsuario(@RequestBody HistoricoEmailDTO usuario) {
		
		try {
			List<HistoricoEmail> historico = historicoEmailService.buscarHistoricoUsuario(usuario.getUsuario());
			historico.stream()
				.forEach(e -> System.out.println(e.toString()));

		} catch (Exception e) {
			log.error("Erro: {}", e.getMessage());
		}

	}
}