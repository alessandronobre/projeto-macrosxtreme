package br.com.devpro.msgeradorpdf.service;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itextpdf.html2pdf.HtmlConverter;

import br.com.devpro.msgeradorpdf.model.Macros;

@Service
public class GeradorPDFService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public byte[] gerarPDF(Macros macros) {
		
		Macros macro = new Macros();
		macro.setUsuario(macros.getUsuario());
		macro.setImc(macros.getImc());
		macro.setTmb(macros.getTmb());
		macro.setGastoCaloricoTotal(macros.getGastoCaloricoTotal());
		macro.setCaloriasTreino(macros.getCaloriasTreino());
		macro.setProteinaTreino(macros.getProteinaTreino());
		macro.setCarboidratoTreino(macros.getCarboidratoTreino());
		macro.setGorduraTreino(macros.getGorduraTreino());
		macro.setFibraTreino(macros.getFibraTreino());
		macro.setCaloriasDescanso(macros.getCaloriasDescanso());
		macro.setProteinaDescanso(macros.getProteinaDescanso());
		macro.setCarboidratoDescanso(macros.getCarboidratoDescanso());
		macro.setGorduraDescanso(macros.getGorduraDescanso());
		macro.setFibraDescanso(macros.getFibraDescanso());
		
		Context context = new Context();
		context.setVariable("macros", macro);

		String html = templateEngine.process("macrospdf.html", context);
		
		ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
		HtmlConverter.convertToPdf(html, pdfStream);
		return pdfStream.toByteArray();
	}
	
	public ResponseEntity<?> downloadPDF(Macros macros) {
		
		Macros macro = new Macros();
		macro.setUsuario(macros.getUsuario());
		macro.setImc(macros.getImc());
		macro.setTmb(macros.getTmb());
		macro.setGastoCaloricoTotal(macros.getGastoCaloricoTotal());
		macro.setCaloriasTreino(macros.getCaloriasTreino());
		macro.setProteinaTreino(macros.getProteinaTreino());
		macro.setCarboidratoTreino(macros.getCarboidratoTreino());
		macro.setGorduraTreino(macros.getGorduraTreino());
		macro.setFibraTreino(macros.getFibraTreino());
		macro.setCaloriasDescanso(macros.getCaloriasDescanso());
		macro.setProteinaDescanso(macros.getProteinaDescanso());
		macro.setCarboidratoDescanso(macros.getCarboidratoDescanso());
		macro.setGorduraDescanso(macros.getGorduraDescanso());
		macro.setFibraDescanso(macros.getFibraDescanso());
		
		Context context = new Context();
		context.setVariable("macros", macro);

		String html = templateEngine.process("macrospdf.html", context);
		
		ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
		HtmlConverter.convertToPdf(html, pdfStream);
		return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=macros.pdf")
					.body(pdfStream.toByteArray());
	}

}
