package com.daniel.organizadorspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.organizadorspring.model.Despesa;
import com.daniel.organizadorspring.repository.DespesaRepository;

@SpringBootApplication
public class OrganizadorSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizadorSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(DespesaRepository despesaRepository) {
		return args -> {
			despesaRepository.deleteAll();
			
			Despesa d = new Despesa();
			d.setName("Água");
			d.setCategory("Contas a pagar");
			d.setPrice(79.50);
			
			despesaRepository.save(d);
		};
	}
}
