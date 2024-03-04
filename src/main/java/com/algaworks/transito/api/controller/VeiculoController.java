package com.algaworks.transito.api.controller;

import com.algaworks.transito.api.model.VeiculoModel;
import com.algaworks.transito.api.model.input.VeiculoInput;
import com.algaworks.transito.api.mapper.VeiculoMapper;
import com.algaworks.transito.domain.model.Veiculo;
import com.algaworks.transito.domain.repository.VeiculoRepository;
import com.algaworks.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    private final VeiculoMapper veiculoMapper;

    @GetMapping
    public List<VeiculoModel> listar() {
        return veiculoMapper.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoMapper::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) { //@Valid habilita as validações criadas na entidade

        Veiculo novoVeiculo = veiculoMapper.toEntity(veiculoInput);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoMapper.toModel(veiculoCadastrado);
    }

}
