package com.daniel.organizadorspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.daniel.organizadorspring.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Despesa SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("_id")
	private Long id;
	
	@NotBlank
	@NotNull
	@Length(min = 3, max = 100)
	@Column(length = 100, nullable = false)
	private String name;
	
	@NotBlank
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "Contas a pagar|Lazer|Entrada")
	@Column(length = 100, nullable = false)
	private Category category;

	@Column(nullable = false)
	private double price;

	@NotBlank
	@NotNull
	@Pattern(regexp = "Ativo|Inativo")
	@Column(length = 100, nullable = false)
	private String status = "Ativo";
}
