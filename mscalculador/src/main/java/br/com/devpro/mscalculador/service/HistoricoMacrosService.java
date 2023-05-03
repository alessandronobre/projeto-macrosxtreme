package br.com.devpro.mscalculador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpro.mscalculador.dto.HistoricoMacrosDTO;
import br.com.devpro.mscalculador.model.HistoricoMacros;
import br.com.devpro.mscalculador.repository.HistoricoMacrosRepository;

@Service
public class HistoricoMacrosService {
	
	@Autowired
	private HistoricoMacrosRepository HistoricoMacrosRepository;
	
	public void salvarHistorico(HistoricoMacrosDTO historicoMacros) {
		
		HistoricoMacros historico = new HistoricoMacros();
		historico.setUsuario(historicoMacros.getUsuario());
		historico.setImc(historicoMacros.getImc());
		historico.setTmb(historicoMacros.getTmb());
		historico.setGastoCaloricoTotal(historicoMacros.getGastoCaloricoTotal());
		historico.setCaloriasTreino(historicoMacros.getCaloriasTreino());
		historico.setProteinaTreino(historicoMacros.getProteinaTreino());
		historico.setCarboidratoTreino(historicoMacros.getCarboidratoTreino());
		historico.setGorduraTreino(historicoMacros.getGorduraTreino());
		historico.setFibraTreino(historicoMacros.getFibraTreino());
		historico.setCaloriasDescanso(historicoMacros.getCaloriasDescanso());
		historico.setProteinaDescanso(historicoMacros.getProteinaDescanso());
		historico.setCarboidratoDescanso(historicoMacros.getCarboidratoDescanso());
		historico.setGorduraDescanso(historicoMacros.getGorduraDescanso());
		historico.setFibraDescanso(historicoMacros.getFibraDescanso());
		HistoricoMacrosRepository.save(historico);
	}
	
	public List<HistoricoMacros> buscarHistorico(String usuario) {
		return HistoricoMacrosRepository.findHistoricoPorUsuario(usuario);
		
	}
}
