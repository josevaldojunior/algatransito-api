package com.algaworks.transito.api.controller;

import com.algaworks.transito.api.mapper.AutuacaoMapper;
import com.algaworks.transito.api.model.AutuacaoModel;
import com.algaworks.transito.api.model.input.AutuacaoInput;
import com.algaworks.transito.domain.model.Autuacao;
import com.algaworks.transito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculo/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoMapper autuacaoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput){
        Autuacao novaAutuacao = autuacaoMapper.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);

        return autuacaoMapper.toModel(autuacaoRegistrada);
    }

}
