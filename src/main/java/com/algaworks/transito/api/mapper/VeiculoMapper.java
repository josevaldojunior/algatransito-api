package com.algaworks.transito.api.mapper;

import com.algaworks.transito.api.dto.VeiculoDTO;
import com.algaworks.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoMapper {

    private final ModelMapper modelMapper;

    public VeiculoDTO toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    public List<VeiculoDTO> toCollection(List<Veiculo> veiculos){
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }

}
