package io.github.thallesryan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.thallesryan.domain.Veiculo;
import io.github.thallesryan.dto.VeiculoRequestDTO;
import io.github.thallesryan.dto.VeiculoResponseDTO;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {


		public static final VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);
		
		public abstract Veiculo toVeiculo(VeiculoRequestDTO veiculo);
		
		public abstract VeiculoResponseDTO toVeiculoResponseDTO(Veiculo veiculo);
	
}
