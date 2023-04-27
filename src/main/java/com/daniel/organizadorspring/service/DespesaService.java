package com.daniel.organizadorspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.daniel.exception.RecordNotFoundException;
import com.daniel.organizadorspring.dto.DespesaDto;
import com.daniel.organizadorspring.dto.mapper.DespesaMapper;
import com.daniel.organizadorspring.repository.DespesaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DespesaService {
    private final DespesaRepository despesaRepository;
    private final DespesaMapper despesaMapper;

    public DespesaService(DespesaRepository despesaRepository, DespesaMapper despesaMapper) {
        this.despesaRepository = despesaRepository;
        this.despesaMapper = despesaMapper;
    }

    public List<DespesaDto> list() {
        return despesaRepository.findAll().stream().map(despesaMapper::toDto).collect(Collectors.toList());
    }

    public DespesaDto findById(@NotNull @Positive Long id) {
        return despesaRepository.findById(id).map(despesaMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public DespesaDto create(@Valid @NotNull DespesaDto despesa) {
        return despesaMapper.toDto(despesaRepository.save(despesaMapper.toEntity(despesa)));
    }

    public DespesaDto update(@NotNull @Positive Long id, @Valid @NotNull DespesaDto despesa) {
        return despesaRepository.findById(id)
                .map(rec -> {
                    rec.setName(despesa.name());
                    rec.setCategory(despesaMapper.convertCategoryValue(despesa.category()));
                    rec.setPrice(despesa.price());

                    return despesaMapper.toDto(despesaRepository.save(rec));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id) {
        despesaRepository.delete(
                despesaRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
