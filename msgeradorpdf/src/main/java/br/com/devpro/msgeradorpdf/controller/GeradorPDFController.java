package br.com.devpro.msgeradorpdf.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devpro.msgeradorpdf.model.Macros;
import br.com.devpro.msgeradorpdf.service.GeradorPDFService;

@RestController
@RequestMapping("/api")
public class GeradorPDFController {
	
	@Autowired
	private GeradorPDFService geradorPDFService;
	
	@GetMapping(value = "/gerar/pdf",produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	private byte[] gerarPdf(@RequestBody Macros macros) throws IOException {
		
		return geradorPDFService.gerarPDF(macros);
		
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	private ResponseEntity<?> downloadPDF(@RequestBody Macros macros) throws IOException {
		
		return geradorPDFService.downloadPDF(macros);
		
	}

}
