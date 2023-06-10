package br.com.devpro.msemail.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.devpro.msemail.dto.EmailDTO;

@FeignClient(name = "MacrosXtremeClient", url = "http://localhost:8080", path = "/api/email")
public interface MacrosXtremeClient {

	@PostMapping("/historico")
	public void salvarHistoricoEmail(@RequestBody EmailDTO email);
	
}
