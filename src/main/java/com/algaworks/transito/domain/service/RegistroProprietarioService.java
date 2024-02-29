package com.algaworks.transito.domain.service;

import com.algaworks.transito.domain.repository.ProprietarioRepository;
import com.algaworks.transito.domain.exception.NegocioException;
import com.algaworks.transito.domain.model.Proprietario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietário não encontrado"));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException("Já existe um proprietário com este e-mail ");
        }

        return proprietarioRepository.save(proprietario);
    }

    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
