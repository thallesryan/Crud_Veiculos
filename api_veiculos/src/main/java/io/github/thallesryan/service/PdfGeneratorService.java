package io.github.thallesryan.service;

import java.awt.Color;
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

	private Table table;

	public void export(HttpServletResponse response, Integer id) throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Veiculo", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		table = new Table(6, 2);
		table.setPadding(4);
		table.setBorderWidth(2);

		VeiculoResponseDTO veiculoResp = veiculoService.findById(id);

		this.addCell("Placa",Color.white,Color.BLUE);
		this.addCell("Chassi", Color.white,Color.BLUE);
		this.addCell("Renavam", Color.white,Color.BLUE);
		this.addCell("Modelo", Color.white,Color.BLUE);
		this.addCell("Marca",Color.white,Color.BLUE);
		this.addCell("Ano", Color.white,Color.BLUE);

		this.addCell(veiculoResp.getPlaca(),Color.black, Color.WHITE);
		this.addCell(veiculoResp.getChassi(),Color.black, Color.WHITE);
		this.addCell(veiculoResp.getRenavam(),Color.black, Color.WHITE);
		this.addCell(veiculoResp.getModelo(),Color.black, Color.WHITE);
		this.addCell(veiculoResp.getMarca() + "",Color.black, Color.WHITE);
		this.addCell(veiculoResp.getAno(),Color.black, Color.WHITE);

		document.add(table);
		document.close();
	}

	private void addCell(String headerName,Color fontColor, Color backGroundColor) {

		Font font = new Font();
		font.setColor(fontColor);
		Cell cell = new Cell();
		cell.add(new Paragraph(headerName, font));
		cell.setBorderWidth(2);
		
		cell.setBackgroundColor(backGroundColor);
		
		
		cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
		cell.setVerticalAlignment(VerticalAlignment.TOP);
		this.table.addCell(cell);
	}

}
