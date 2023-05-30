package br.com.devpro.msemail.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class HistoricoEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_hist_email")
	private Long id;
	
	@Column(nullable = false)
	private String usuario;
	
	@Column(name="titulo_email", nullable = false)
	private String tituloEmail;
	
	@Column(nullable = false)
	private String conteudo;
	
	@Column(name="data_envio", nullable = false)
	private String dataEnvio;

	@Column(nullable = false)
	private String destinatario;

	
}
