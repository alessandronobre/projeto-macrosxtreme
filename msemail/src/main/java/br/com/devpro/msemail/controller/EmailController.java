package br.com.devpro.msemail.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devpro.msemail.dto.HistoricoEmailDTO;
import br.com.devpro.msemail.model.HistoricoEmail;
import br.com.devpro.msemail.service.EmailService;
import br.com.devpro.msemail.service.HistoricoEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private HistoricoEmailService historicoEmailService;

	@PostMapping
	public void enviar(@RequestBody HistoricoEmailDTO historicoEmail) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		String dataHoraFormatada = dataHoraAtual.format(formatador);

		HistoricoEmailDTO histEmail = new HistoricoEmailDTO();
		histEmail.setUsuario(histEmail.getUsuario());
		histEmail.setTituloEmail(histEmail.getTituloEmail());
		histEmail.setConteudo(histEmail.getConteudo());
		histEmail.setDataEnvio(dataHoraFormatada);
		histEmail.setDestinatario(histEmail.getDestinatario());

		try {
//			emailService.sendSimpleMail("alessandronobre.ti@gmail.com", "Teste", "TESTE");

			historicoEmailService.salvarHistorico(histEmail);

		} catch (Exception e) {
			log.error("Erro: {}", e.getMessage());
		}

	}
	
    @GetMapping(params = "nome")
    public ResponseEntity<List<HistoricoEmailDTO>> findHistoricoPorUsuario(@RequestParam("nome") String nome){
        List<HistoricoEmailDTO> lista = new ArrayList<>();
		try {
			lista = historicoEmailService.findHistoricoPorUsuario(nome);
		} catch (Exception e) {
			e.getMessage();
			ResponseEntity.noContent();
			
		}
        return ResponseEntity.ok(lista);
    }
}