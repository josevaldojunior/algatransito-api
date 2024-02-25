package com.algaworks.transito.domain.model;

import com.algaworks.transito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //(onlyExplicitlyIncluded = true) -> cria o EqualsAndHashCode apenas para o que for incluido
@Entity
public class Proprietario {

    @NotNull(groups = ValidationGroups.ProprietarioId.class) //Informando que a validação é apenas para o grupo ProprietarioId
    @EqualsAndHashCode.Include //Inclui o Id para gerar o EqualsAndHashCode
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //Não permite null ou vazio
    @Size(max = 60) //Valida o tamanho do parametro que vai ser passado
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "fone")
    private String telefone;

}
