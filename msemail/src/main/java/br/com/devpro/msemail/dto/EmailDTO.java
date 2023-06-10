package br.com.devpro.msemail.dto;

import br.com.devpro.msemail.Model.Usuario;
import lombok.Data;

@Data
public class EmailDTO {
	
	private Usuario usuario;
	private String tituloEmail;
	private String conteudo;
	private String dataEnvio;
	private String destinatario;
	
}
