package com.exdemplo.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exdemplo.biblioteca.entity.Leitor;

public interface LeitorRepository extends CrudRepository<Leitor, Long> {

    List<Leitor> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmailIgnoreCase(String email);
}