package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @EqualsAndHashCode.Include
    private Long id;

    @Valid // Ativa validação adicionada no entidade 'Proprietario'
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class) // converte o grupo default pra o grupo criado
    @NotNull //Não permite null, não usar @NotBlank pois não é uma string
    @ManyToOne
    private Proprietario proprietario;

    @NotBlank //Não permite null
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") //padroniza a placa, permitido o padrão novo e antigo ex.: XXX0000 ou XXX0X000
    private String placa;

    @JsonProperty(access = Access.READ_ONLY) //Atribuio o parametro apenas para leitura, não permitindo ser passado via Json
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;

}
