package br.com.devpro.mscalculador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.devpro.mscalculador.dto.Usuario;
import br.com.devpro.mscalculador.service.CalculadorService;

@RestController
@RequestMapping("/api")
public class CalculadorController {

	@Autowired
	private CalculadorService calculadorService;

	@PostMapping("/calcular")
	public void calculador(@RequestBody Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("calculator/result");

		modelAndView.addObject("tmb", calculadorService.calcularTBM(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso()));
		modelAndView.addObject("gastoT", calculadorService.calcularGT(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getNivelAtividadeFisica()));
		modelAndView.addObject("objT", calculadorService.objetivoTreino(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica()));
		modelAndView.addObject("objOff", calculadorService.objetivoDescanso(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica()));
		modelAndView.addObject("macrosT", calculadorService.macrosTreino(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica()));
		modelAndView.addObject("macrosOff", calculadorService.macrosDescanso(usuario.getGenero(), usuario.getIdade(), usuario.getAltura(), usuario.getPeso(), usuario.getObjetivo(), usuario.getNivelAtividadeFisica()));
		modelAndView.addObject("imc", calculadorService.imc(usuario.getAltura(), usuario.getPeso()));

	}
}
