package com.daniel.organizadorspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.organizadorspring.dto.DespesaDto;
import com.daniel.organizadorspring.service.DespesaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/despesas")
public class DespesaController {

	private final DespesaService despesaService;

	public DespesaController(DespesaService despesaService) {
		this.despesaService = despesaService;
	}

	@GetMapping
	public List<DespesaDto> list() {
		return despesaService.list();
	}

	@GetMapping("/{id}")
	public DespesaDto findById(@PathVariable @NotNull @Positive Long id) {
		return despesaService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public DespesaDto create(@RequestBody @Valid @NotNull DespesaDto despesa) {
		return despesaService.create(despesa);
	}

	@PutMapping("/{id}")
	public DespesaDto update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid @NotNull DespesaDto despesa) {
		return despesaService.update(id, despesa);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		despesaService.delete(id);
	}
}
