package io.github.thallesryan.dto;


import io.github.thallesryan.domain.enums.VeiculoMarcaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class VeiculoRequestDTO {

    private String placa;

    private String chassi;

    private String renavam;

    private String modelo;
    
    private VeiculoMarcaEnum marca;
    
    private String ano;

}
