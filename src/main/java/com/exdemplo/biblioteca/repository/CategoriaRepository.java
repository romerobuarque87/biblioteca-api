package com.exdemplo.biblioteca.repository;

import com.exdemplo.biblioteca.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}