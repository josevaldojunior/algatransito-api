package com.algaworks.transito.api.model;

import com.algaworks.transito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VeiculoModel {

    private Long id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataApreensao;

}
