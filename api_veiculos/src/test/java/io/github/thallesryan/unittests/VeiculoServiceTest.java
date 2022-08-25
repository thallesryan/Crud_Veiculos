package io.github.thallesryan.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.thallesryan.domain.Veiculo;
import io.github.thallesryan.dto.VeiculoResponseDTO;
import io.github.thallesryan.repository.VeiculoRepository;
import io.github.thallesryan.service.VeiculoService;

class VeiculoServiceTest {

	Veiculo mockVeiculo;
	
	@InjectMocks
	private VeiculoService service;
	
	@Mock
	VeiculoRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		mockVeiculo = new Veiculo();
		MockitoAnnotations.openMocks(this);
		
		this.mockVeiculo = mockVeiculo(1);
		
		
	}
	
	private Veiculo mockVeiculo(Integer n) {
		mockVeiculo = new Veiculo();
		mockVeiculo.setId(n);
		mockVeiculo.setPlaca("Placa test" + n);
		mockVeiculo.setChassi("Chassi test" + n);
		mockVeiculo.setRenavam("Renavam test" + n);
		mockVeiculo.setModelo("Modelo test" + n);
		mockVeiculo.setAno("Ano test" + n);
		return mockVeiculo;
	}

	

	@Test
	void testFindById() {
		
		Veiculo entity = mockVeiculo;
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		var result = service.findById(1);
		
		assertNotNull(result);
		assertNotNull(entity.getId());
		
		assertEquals("Placa test1", entity.getPlaca());
		assertEquals("Chassi test1", entity.getChassi());
		assertEquals("Renavam test1", entity.getRenavam());
		assertEquals("Modelo test1", entity.getModelo());
		assertEquals("Ano test1", entity.getAno());
	}


	@Test
	void testDelete() {
		Veiculo entity = this.mockVeiculo(1); 
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		service.delete(1);
	}
	
	@Test
	void testMapperToDTO() {
		mockVeiculo = mockVeiculo(1);
		VeiculoResponseDTO dto = service.toDTO(mockVeiculo);
		
		assertEquals(mockVeiculo.getPlaca(), dto.getPlaca());
		assertEquals(mockVeiculo.getChassi(), dto.getChassi());
		assertEquals(mockVeiculo.getRenavam(), dto.getRenavam());
		assertEquals(mockVeiculo.getModelo(), dto.getModelo());
		assertEquals(mockVeiculo.getAno(), dto.getAno());
	}

}
