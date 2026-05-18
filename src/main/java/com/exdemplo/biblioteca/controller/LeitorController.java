package com.exdemplo.biblioteca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exdemplo.biblioteca.entity.Leitor;
import com.exdemplo.biblioteca.repository.LeitorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/leitores")
@RequiredArgsConstructor
public class LeitorController {

    private final LeitorRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Leitor>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leitor> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}