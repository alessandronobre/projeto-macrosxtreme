package br.com.devpro.mscalculador.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String nome;
	private String genero;
	private int idade;
	private int altura;
	private int peso;
	private String objetivo;
	private String nivelAtividadeFisica;
}
