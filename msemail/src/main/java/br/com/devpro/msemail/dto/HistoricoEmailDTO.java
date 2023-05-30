package br.com.devpro.msemail.dto;

import br.com.devpro.msemail.model.HistoricoEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoricoEmailDTO {
	
	private String usuario;
	private String tituloEmail;
	private String conteudo;
	private String dataEnvio;
	private String destinatario;
	
	public HistoricoEmailDTO(HistoricoEmail historicoEmai) {
		this.usuario = historicoEmai.getUsuario();
		this.tituloEmail = historicoEmai.getTituloEmail();
		this.conteudo = historicoEmai.getConteudo();
		this.dataEnvio = historicoEmai.getDataEnvio();
		this.destinatario = historicoEmai.getDestinatario();
	}

}
