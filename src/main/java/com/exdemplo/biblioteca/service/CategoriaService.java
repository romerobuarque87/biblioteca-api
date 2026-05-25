package com.exdemplo.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exdemplo.biblioteca.entity.Categoria;
import com.exdemplo.biblioteca.exception.ResourceNotFoundException;
import com.exdemplo.biblioteca.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return (List<Categoria>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria", id));
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria categoria) {

        Categoria categoriaExistente = buscarPorId(id);

        categoriaExistente.setNome(categoria.getNome());
        categoriaExistente.setDescricao(categoria.getDescricao());

        return repository.save(categoriaExistente);
    }

    @Transactional
    public void deletar(Long id) {

        Categoria categoria = buscarPorId(id);

        repository.delete(categoria);
    }
}