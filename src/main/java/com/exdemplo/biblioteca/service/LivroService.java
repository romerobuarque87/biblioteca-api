package com.exdemplo.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exdemplo.biblioteca.entity.Livro;
import com.exdemplo.biblioteca.exception.ResourceNotFoundException;
import com.exdemplo.biblioteca.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    @Transactional(readOnly = true)
    public List<Livro> listar() {
        return (List<Livro>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro", id));
    }

    @Transactional
    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    @Transactional
    public Livro atualizar(Long id, Livro livro) {
        Livro livroExistente = buscarPorId(id);

        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setCategoria(livro.getCategoria());

        return repository.save(livroExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        repository.delete(livro);
    }
}