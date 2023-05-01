package br.com.devpro.mscalculador.dto;

import lombok.Data;

@Data
public class Usuario {

	private String genero;
	private int idade;
	private int altura;
	private int peso;
	private String objetivo;
	private String nivelAtividadeFisica;
}
