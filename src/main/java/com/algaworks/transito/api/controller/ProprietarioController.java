package com.algaworks.transito.api.controller;

import com.algaworks.transito.api.assembler.ProprietarioAssembler;
import com.algaworks.transito.api.model.ProprietarioModel;
import com.algaworks.transito.api.model.input.ProprietarioInput;
import com.algaworks.transito.domain.model.Proprietario;
import com.algaworks.transito.domain.repository.ProprietarioRepository;
import com.algaworks.transito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioAssembler proprietarioAssembler;

    @GetMapping
    public List<ProprietarioModel> listar() {
        return proprietarioAssembler.toCollectionModel(proprietarioRepository.findAll());
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioModel> buscar(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(proprietarioAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioModel cadastrar(@Valid @RequestBody ProprietarioInput proprietarioInput) {

        Proprietario novoProprietario = proprietarioAssembler.toEntity(proprietarioInput);
        Proprietario proprietarioCadastrado = registroProprietarioService.salvar(novoProprietario);

        return proprietarioAssembler.toModel(proprietarioCadastrado);
    }

    @PutMapping("{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @Valid @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {

        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);

        return ResponseEntity.noContent().build();
    }

}