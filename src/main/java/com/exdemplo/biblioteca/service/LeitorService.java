package com.exdemplo.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exdemplo.biblioteca.entity.Leitor;
import com.exdemplo.biblioteca.exception.ResourceNotFoundException;
import com.exdemplo.biblioteca.repository.LeitorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeitorService {

    private final LeitorRepository repository;

    @Transactional(readOnly = true)
    public List<Leitor> listar() {
        return (List<Leitor>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Leitor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leitor", id));
    }

    @Transactional
    public Leitor salvar(Leitor leitor) {
        return repository.save(leitor);
    }

    @Transactional
    public Leitor atualizar(Long id, Leitor leitor) {
        Leitor leitorExistente = buscarPorId(id);

        leitorExistente.setNome(leitor.getNome());
        leitorExistente.setEmail(leitor.getEmail());
        leitorExistente.setTelefone(leitor.getTelefone());

        return repository.save(leitorExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Leitor leitor = buscarPorId(id);
        repository.delete(leitor);
    }
}