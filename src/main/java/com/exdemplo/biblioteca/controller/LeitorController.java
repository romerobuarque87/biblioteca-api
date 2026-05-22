package com.exdemplo.biblioteca.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exdemplo.biblioteca.entity.Leitor;
import com.exdemplo.biblioteca.service.LeitorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/leitores")
@RequiredArgsConstructor
public class LeitorController {

    private final LeitorService service;

    @GetMapping
    public ResponseEntity<Iterable<Leitor>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leitor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Leitor> criar(@Valid @RequestBody Leitor leitor) {
        Leitor salvo = service.salvar(leitor);
        return ResponseEntity
                .created(URI.create("/api/v1/leitores/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leitor> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Leitor leitor) {

        return ResponseEntity.ok(service.atualizar(id, leitor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}