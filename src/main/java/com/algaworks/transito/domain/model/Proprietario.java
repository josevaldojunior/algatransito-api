package com.algaworks.transito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //(onlyExplicitlyIncluded = true) -> cria o EqualsAndHashCode apenas para o que for incluido
@Entity
public class Proprietario {

    @EqualsAndHashCode.Include //Inclui o Id para gerar o EqualsAndHashCode
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @Column(name = "fone")
    private String telefone;

}
