package com.algaworks.transito.domain.service;

import com.algaworks.transito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.transito.domain.exception.NegocioException;
import com.algaworks.transito.domain.model.Proprietario;
import com.algaworks.transito.domain.model.StatusVeiculo;
import com.algaworks.transito.domain.model.Veiculo;
import com.algaworks.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoveiculo) {

        if (novoveiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoveiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoveiculo)).isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoveiculo.getProprietario().getId());

        novoveiculo.setProprietario(proprietario);
        novoveiculo.setStatus(StatusVeiculo.REGULAR);
        novoveiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoveiculo);

    }

}
