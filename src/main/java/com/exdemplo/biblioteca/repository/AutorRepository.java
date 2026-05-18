package com.exdemplo.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exdemplo.biblioteca.entity.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}