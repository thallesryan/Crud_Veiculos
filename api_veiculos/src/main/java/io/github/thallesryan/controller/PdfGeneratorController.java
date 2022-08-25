package io.github.thallesryan.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.thallesryan.service.PdfGeneratorService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class PdfGeneratorController {
	
	 private final PdfGeneratorService pdfGeneratorService;

	    public PdfGeneratorController(PdfGeneratorService pdfGeneratorService) {
	        this.pdfGeneratorService = pdfGeneratorService;
	    }

	    @GetMapping("/veiculos/pdf/{id}")
	    public void generatePDF(HttpServletResponse response, @PathVariable Integer id) throws IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);

	        this.pdfGeneratorService.export(response, id);
	    }

}
