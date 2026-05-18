package com.exdemplo.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exdemplo.biblioteca.entity.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}