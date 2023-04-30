package br.com.devpro.msemail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpro.msemail.dto.HistoricoEmailDTO;
import br.com.devpro.msemail.model.HistoricoEmail;
import br.com.devpro.msemail.repository.HistoricoEmailRepository;

@Service
public class HistoricoEmailService {

	@Autowired
	private HistoricoEmailRepository historicoEmailRepository;

	public void salvarHistorico(HistoricoEmailDTO historicoEmail) throws Exception {

		if (historicoEmail != null) {
			HistoricoEmail novoHistorico = new HistoricoEmail();
			novoHistorico.setUsuario(historicoEmail.getUsuario());
			novoHistorico.setTituloEmail(historicoEmail.getTituloEmail());
			novoHistorico.setConteudo(historicoEmail.getConteudo());
			novoHistorico.setDataEnvio(historicoEmail.getDataEnvio());
			novoHistorico.setDestinatario(historicoEmail.getDestinatario());
			historicoEmailRepository.save(novoHistorico);

		}

	}

	public List<HistoricoEmail> buscarHistoricoUsuario(String usuario) throws Exception {
		List<HistoricoEmail> historico = historicoEmailRepository.findHistoricoPorUsuario(usuario);
		
		if (!historico.isEmpty()) {
			return historico;
		}
		 throw new Exception("Não há historico de email para esse usuario");

	}

}
