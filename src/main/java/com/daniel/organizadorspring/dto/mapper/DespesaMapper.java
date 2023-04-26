package com.daniel.organizadorspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.daniel.organizadorspring.dto.DespesaDto;
import com.daniel.organizadorspring.model.Despesa;

@Component
public class DespesaMapper {

    public DespesaDto toDto(Despesa despesa) {
        if (despesa == null) {
            return null;
        }

        return new DespesaDto(despesa.getId(), despesa.getName(), despesa.getCategory(), despesa.getPrice());
    }

    public Despesa toEntity(DespesaDto despesaDto) {
        if (despesaDto == null) {
            return null;
        }

        Despesa despesa = new Despesa();

        if (despesaDto.id() != null) {
            despesa.setId(despesaDto.id());
        }
        despesa.setName(despesaDto.name());
        despesa.setCategory(despesaDto.category());
        despesa.setPrice(despesaDto.price());
        despesa.setStatus("Ativo");

        return despesa;

    }
}
