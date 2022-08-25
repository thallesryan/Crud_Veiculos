package io.github.thallesryan.integrationtests.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.thallesryan.controller.VeiculoController;
import io.github.thallesryan.domain.Veiculo;
import io.github.thallesryan.dto.VeiculoRequestDTO;
import io.github.thallesryan.dto.VeiculoResponseDTO;
import io.github.thallesryan.service.VeiculoService;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VeiculoService service;
	
	private VeiculoResponseDTO mockVeiculoResponse;
	
	private VeiculoRequestDTO mockRequestVeiculo;
	
	@Test
	void create() throws Exception {
		
		mockMvc.perform(post("/veiculos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockRequestVeiculo(1))))
				.andExpect(status().is(201));
	}
	
	@Test
	void testFindById() throws Exception {
		
		when(service.findById(1)).thenReturn(this.mockVeiculoResponse(1));
		
		mockMvc.perform(get("/veiculos/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("placa").value("Placa test1"))
				.andExpect(jsonPath("chassi").value("Chassi test1"))
				.andExpect(jsonPath("renavam").value("Renavam test1"))
				.andExpect(jsonPath("modelo").value("Modelo test1"))
				.andExpect(jsonPath("ano").value("Ano test1"))
				;
				
		verify(service).findById(1);
	}
	
	@Test
	void update() throws Exception {
		
		mockMvc.perform(put("/veiculos/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockRequestVeiculo(2))))
				.andExpectAll(status().isOk());
	}
	
	@Test
	void deleteVeiculo() throws Exception {
		mockMvc.perform(delete("/veiculos/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	private VeiculoResponseDTO mockVeiculoResponse(Integer n) {
		mockVeiculoResponse = new VeiculoResponseDTO();
		
		mockVeiculoResponse.setId(n);
		mockVeiculoResponse.setPlaca("Placa test" + n);
		mockVeiculoResponse.setChassi("Chassi test" + n);
		mockVeiculoResponse.setRenavam("Renavam test" + n);
		mockVeiculoResponse.setModelo("Modelo test" + n);
		mockVeiculoResponse.setAno("Ano test" + n);
		return mockVeiculoResponse;
	}
	
	private VeiculoRequestDTO mockRequestVeiculo(Integer n) {
		mockRequestVeiculo = new VeiculoRequestDTO();
		
		mockRequestVeiculo.setPlaca("Placa test" + n);
		mockRequestVeiculo.setChassi("Chassi test" + n);
		mockRequestVeiculo.setRenavam("Renavam test" + n);
		mockRequestVeiculo.setModelo("Modelo test" + n);
		mockRequestVeiculo.setAno("Ano test" + n);
		return mockRequestVeiculo;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
