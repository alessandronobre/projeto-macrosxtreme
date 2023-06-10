package br.com.devpro.msemail.Model;

import lombok.Data;

@Data
public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String password;
}
