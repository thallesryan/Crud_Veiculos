package io.github.thallesryan.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.thallesryan.domain.Veiculo;
import io.github.thallesryan.dto.VeiculoRequestDTO;
import io.github.thallesryan.dto.VeiculoResponseDTO;
import io.github.thallesryan.exception.ResourceNotFoundException;
import io.github.thallesryan.repository.VeiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;

	private Logger logger = Logger.getLogger(VeiculoService.class.getName());

	@Transactional(rollbackOn = Exception.class)
	public void create(VeiculoRequestDTO veiculoRequestDTO) {
		logger.info("Creating one Veiculo");

		Veiculo entity = this.toEntity(veiculoRequestDTO);
		repository.save(entity);
	}

	public VeiculoResponseDTO findById(Integer id) {
		logger.info("Finding Veiculo By Id...");

		Veiculo entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum veículo com o id " + id + " foi encontrado."));

		return this.toDTO(entity);
	}

	@Transactional(rollbackOn = Exception.class)
	public void update(Integer id, VeiculoRequestDTO veiculoRequest) {

		Veiculo veiculo = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("O Veiculo que será atualizado não foi encontrado."));
		veiculo.setPlaca(veiculoRequest.getPlaca());
		veiculo.setChassi(veiculoRequest.getChassi());
		veiculo.setRenavam(veiculoRequest.getRenavam());
		veiculo.setModelo(veiculoRequest.getModelo());
		veiculo.setMarca(veiculoRequest.getMarca());
		veiculo.setAno(veiculoRequest.getAno());

		repository.save(veiculo);
	}

	@Transactional(rollbackOn = Exception.class)
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	private Veiculo toEntity(VeiculoRequestDTO veiculoDTO) {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca(veiculoDTO.getPlaca());
		veiculo.setChassi(veiculoDTO.getChassi());
		veiculo.setRenavam(veiculoDTO.getRenavam());
		veiculo.setModelo(veiculoDTO.getModelo());
		veiculo.setMarca(veiculoDTO.getMarca());
		veiculo.setAno(veiculoDTO.getAno());

		return veiculo;
	}

	private VeiculoResponseDTO toDTO(Veiculo veiculo) {
		VeiculoResponseDTO veiculoResponse = new VeiculoResponseDTO();

		veiculoResponse.setId(veiculo.getId());
		veiculoResponse.setPlaca(veiculo.getPlaca());
		veiculoResponse.setRenavam(veiculo.getRenavam());
		veiculoResponse.setModelo(veiculo.getModelo());
		veiculoResponse.setMarca(veiculo.getMarca());
		veiculoResponse.setAno(veiculo.getAno());

		return veiculoResponse;

	}
}
