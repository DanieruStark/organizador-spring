package com.daniel.organizadorspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.organizadorspring.model.Despesa;
import com.daniel.organizadorspring.repository.DespesaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/despesas")
@AllArgsConstructor
public class DespesaController {

	private final DespesaRepository despesaRepository = null;
	
	@GetMapping
	public List<Despesa> list(){
		return despesaRepository.findAll();
	}
}
