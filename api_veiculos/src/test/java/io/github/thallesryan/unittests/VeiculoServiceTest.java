package io.github.thallesryan.unittests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import io.github.thallesryan.domain.Veiculo;
import io.github.thallesryan.service.VeiculoService;

class VeiculoServiceTest {

	Veiculo mockVeiculo;
	
	@InjectMocks
	private VeiculoService service;
	
	@BeforeEach
	void setUp() throws Exception {
		
		mockVeiculo = new Veiculo();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
