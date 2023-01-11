package com.daniel.organizadorspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.organizadorspring.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
