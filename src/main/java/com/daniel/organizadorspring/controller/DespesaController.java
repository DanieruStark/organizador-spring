package com.daniel.organizadorspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/despesas")
@AllArgsConstructor
public class DespesaController {

	private final DespesaRepository despesaRepository;

	@GetMapping
	public @ResponseBody List<Despesa> list() {
		return despesaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable Long id) {
		return despesaRepository.findById(id)
				.map(rec -> ResponseEntity.ok().body(rec))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Despesa create(@RequestBody Despesa despesa) {
		return despesaRepository.save(despesa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa despesa) {
		return despesaRepository.findById(id)
				.map(rec -> {
					rec.setName(despesa.getName());
					rec.setCategory(despesa.getCategory());
					rec.setPrice(despesa.getPrice());

					Despesa updated = despesaRepository.save(rec);
					return ResponseEntity.ok().body(updated);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
