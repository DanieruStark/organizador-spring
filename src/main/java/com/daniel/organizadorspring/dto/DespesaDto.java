package com.daniel.organizadorspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DespesaDto(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 3, max = 100) String name,
        @NotBlank @NotNull @Length(max = 30) @Pattern(regexp = "Contas a pagar|Lazer|Entrada") String category,
        double price) {

}
