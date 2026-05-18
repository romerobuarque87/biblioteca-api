package com.exdemplo.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exdemplo.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {

    List<Emprestimo> findByDevolvidoFalse();

    List<Emprestimo> findByLeitorId(Long leitorId);

    List<Emprestimo> findByLivroId(Long livroId);
}