package com.algaworks.transito.api.model.input;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProprietarioInput {

    @NotBlank //Não permite null ou vazio
    @Size(max = 60) //Valida o tamanho do parametro que vai ser passado
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;

}
