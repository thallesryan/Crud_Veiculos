package io.github.thallesryan.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.thallesryan.dto.VeiculoRequestDTO;
import io.github.thallesryan.dto.VeiculoResponseDTO;
import io.github.thallesryan.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin
public class VeiculoController{

	@Autowired
	private VeiculoService service;
	
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createVeiculo(@RequestBody VeiculoRequestDTO veiculoRequestDTO){
    	service.create(veiculoRequestDTO);

    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public VeiculoResponseDTO findById(@PathVariable Integer id) {
    	return service.findById(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateVeiculo(@PathVariable Integer id, @RequestBody VeiculoRequestDTO veiculo) {
    	service.update(id, veiculo);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
    	service.delete(id);
    }

    @GetMapping
    public List<VeiculoResponseDTO> findAll(){
    	return service.findAll();	
    }
}
