package com.algaworks.transito.domain.service;

import com.algaworks.transito.domain.model.Autuacao;
import com.algaworks.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);
    }

}
