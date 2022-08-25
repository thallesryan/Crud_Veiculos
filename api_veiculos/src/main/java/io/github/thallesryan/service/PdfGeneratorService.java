package io.github.thallesryan.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;

import io.github.thallesryan.dto.VeiculoResponseDTO;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfGeneratorService {
	
	@Autowired
	private VeiculoService veiculoService;
	

	
	public void export(HttpServletResponse response, Integer id) throws DocumentException, IOException {
		
		 Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());

	        document.open();
	        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        fontTitle.setSize(18);

	        Paragraph paragraph = new Paragraph("Veiculo", fontTitle);
	        paragraph.setAlignment(Paragraph.ALIGN_CENTER);



	        
	        document.add(paragraph);
    
	        Table table = new Table(6, 2);
	        table.setPadding(4);
	        
	        VeiculoResponseDTO veiculoResp = veiculoService.findById(id);
	        
	      
	       table = this.addHeader(table, "Placa");
	       table = this.addHeader(table, "Chassi");
	       table = this.addHeader(table, "Renavam");
	       table = this.addHeader(table, "Modelo");
	       table = this.addHeader(table, "Marca");
	       table = this.addHeader(table, "Ano");
	       
	       table = this.addHeader(table, veiculoResp.getPlaca());
	       table = this.addHeader(table, veiculoResp.getChassi());
	       table = this.addHeader(table, veiculoResp.getRenavam());
	       table = this.addHeader(table, veiculoResp.getModelo());
	       table = this.addHeader(table, veiculoResp.getMarca() + "");
	       table = this.addHeader(table, veiculoResp.getAno());

	        
	        document.add(table);
	        document.close();
	}
	
	private Table addHeader(Table table, String headerName) {
		
		Cell cell = new Cell(headerName);
		table.addCell(cell);
		
		  cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	       cell.setVerticalAlignment(VerticalAlignment.TOP);
		
		return table;
	}

}
