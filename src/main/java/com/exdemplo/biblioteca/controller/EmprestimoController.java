package com.exdemplo.biblioteca.controller;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exdemplo.biblioteca.entity.Emprestimo;
import com.exdemplo.biblioteca.service.EmprestimoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;

    @GetMapping
    public ResponseEntity<Iterable<Emprestimo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criar(@Valid @RequestBody Emprestimo emprestimo) {
        Emprestimo salvo = service.salvar(emprestimo);
        return ResponseEntity
                .created(URI.create("/api/v1/emprestimos/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Emprestimo emprestimo) {

        return ResponseEntity.ok(service.atualizar(id, emprestimo));
    }

    @PatchMapping("/{id}/devolucao")
    public ResponseEntity<Emprestimo> registrarDevolucao(@PathVariable Long id) {
        return ResponseEntity.ok(service.registrarDevolucao(id));
    }

    @GetMapping("/{id}/multa")
    public ResponseEntity<BigDecimal> calcularMulta(@PathVariable Long id) {
        Emprestimo emprestimo = service.buscarPorId(id);
        return ResponseEntity.ok(service.calcularMulta(emprestimo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}