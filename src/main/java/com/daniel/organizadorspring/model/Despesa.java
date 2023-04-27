package com.daniel.organizadorspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.daniel.organizadorspring.enums.Category;
import com.daniel.organizadorspring.enums.Status;
import com.daniel.organizadorspring.enums.converters.CategoryConverter;
import com.daniel.organizadorspring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@Column(length = 100,nullable = false)
	@Convert(converter = CategoryConverter.class)
	private Category category;

	@Column(nullable = false)
	private double price;

	@NotBlank
	@NotNull
	@Column(length = 100, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ATIVO;
}
