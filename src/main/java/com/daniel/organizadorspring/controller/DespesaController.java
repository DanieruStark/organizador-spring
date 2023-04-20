package com.daniel.organizadorspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.organizadorspring.model.Despesa;
import com.daniel.organizadorspring.repository.DespesaRepository;
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
	public @ResponseBody List<Despesa> list() {
		return despesaService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable @NotNull @Positive Long id) {
		return despesaService.findById(id)
				.map(rec -> ResponseEntity.ok().body(rec))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Despesa create(@RequestBody @Valid Despesa despesa) {
		return despesaService.create(despesa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Despesa> update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid Despesa despesa) {
		return despesaService.update(id, despesa)
				.map(rec ->	ResponseEntity.ok().body(rec))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(despesaService.delete(id)){
			return ResponseEntity.noContent().<Void>build();
		}
		return ResponseEntity.notFound().build();
	}
}
