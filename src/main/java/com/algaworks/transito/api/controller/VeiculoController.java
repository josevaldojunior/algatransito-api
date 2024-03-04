package com.algaworks.transito.api.controller;

import com.algaworks.transito.api.dto.VeiculoDTO;
import com.algaworks.transito.domain.model.Veiculo;
import com.algaworks.transito.domain.repository.VeiculoRepository;
import com.algaworks.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) { //@Valid habilita as validações criadas na entidade
        return registroVeiculoService.cadastrar(veiculo);
    }

}
