package br.com.devpro.mscalculador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devpro.mscalculador.dto.HistoricoMacrosDTO;
import br.com.devpro.mscalculador.dto.UsuarioDTO;
import br.com.devpro.mscalculador.model.HistoricoMacros;
import br.com.devpro.mscalculador.service.CalculadorService;
import br.com.devpro.mscalculador.service.HistoricoMacrosService;

@RestController
@RequestMapping("/api")
public class CalculadorController {

	@Autowired
	private CalculadorService calculadorService;
	
	@Autowired
	private HistoricoMacrosService historicoMacrosService;

	@PostMapping("/calcular")
	public void calculador(@RequestBody UsuarioDTO usuario) {

		String imc = calculadorService.imc(usuario.getAltura(), usuario.getPeso());
		Integer tmb =  calculadorService.calcularTBM(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso());
		Integer gastoCaloricoTotal = calculadorService.calcularGT(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getNivelAtividadeFisica());
		Integer objetivoTreino = calculadorService.objetivoTreino(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica());
		Integer objetivoDescanso = calculadorService.objetivoDescanso(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica());
		List<Integer> macrosTreino = calculadorService.macrosTreino(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica());
		List<Integer> macrosDescanso = calculadorService.macrosDescanso(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica());
		
		HistoricoMacrosDTO historicoMacrosDTO = new HistoricoMacrosDTO();
		historicoMacrosDTO.setUsuario(usuario.getNome());
		historicoMacrosDTO.setImc(imc);
		historicoMacrosDTO.setTmb(tmb);
		historicoMacrosDTO.setGastoCaloricoTotal(gastoCaloricoTotal);
		historicoMacrosDTO.setCaloriasTreino(objetivoTreino);
		historicoMacrosDTO.setProteinaTreino(macrosTreino.get(0));
		historicoMacrosDTO.setCarboidratoTreino(macrosTreino.get(1));
		historicoMacrosDTO.setGorduraTreino(macrosTreino.get(2));
		historicoMacrosDTO.setFibraTreino(macrosTreino.get(3));
		historicoMacrosDTO.setCaloriasDescanso(objetivoDescanso);
		historicoMacrosDTO.setProteinaDescanso(macrosDescanso.get(0));
		historicoMacrosDTO.setCarboidratoDescanso(macrosDescanso.get(1));
		historicoMacrosDTO.setGorduraDescanso(macrosDescanso.get(2));
		historicoMacrosDTO.setFibraDescanso(macrosDescanso.get(3));
		
		historicoMacrosService.salvarHistorico(historicoMacrosDTO);
		
	}
	
	@GetMapping("/buscar/historico")
	public void buscarHistorico(@RequestBody UsuarioDTO usuario) {
		
		List<HistoricoMacros> historico = historicoMacrosService.buscarHistorico(usuario.getNome());
		historico.stream()
			.forEach(e -> System.out.println(e.toString()));
	}
}
