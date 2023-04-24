package com.daniel.organizadorspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.exception.RecordNotFoundException;
import com.daniel.organizadorspring.model.Despesa;
import com.daniel.organizadorspring.repository.DespesaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class DespesaService {
    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<Despesa> list() {
        return despesaRepository.findAll();
    }

    public Despesa findById(@PathVariable @NotNull @Positive Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Despesa create(@Valid Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public Despesa update(@NotNull @Positive Long id, @Valid Despesa despesa) {
        return despesaRepository.findById(id)
                .map(rec -> {
                    rec.setName(despesa.getName());
                    rec.setCategory(despesa.getCategory());
                    rec.setPrice(despesa.getPrice());

                    return despesaRepository.save(rec);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable Long id) {
        despesaRepository.delete(
            despesaRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
