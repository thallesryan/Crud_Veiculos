package io.github.thallesryan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.thallesryan.domain.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

}
