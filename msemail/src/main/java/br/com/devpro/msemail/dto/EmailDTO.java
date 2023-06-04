package br.com.devpro.msemail.dto;

import lombok.Data;

@Data
public class EmailDTO {
	
	private String usuario;
	private String tituloEmail;
	private String conteudo;
	private String dataEnvio;
	private String destinatario;
	
}
