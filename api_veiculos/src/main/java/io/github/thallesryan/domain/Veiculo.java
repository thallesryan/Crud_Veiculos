package io.github.thallesryan.domain;

import io.github.thallesryan.domain.enums.VeiculoMarcaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Placa não pode ser vazio!")
    private String placa;

    private String chassi;

    private String renavam;

    private String modelo;

//    @Enumerated(EnumType.STRING)
    private VeiculoMarcaEnum marca;

    private String ano;

}
