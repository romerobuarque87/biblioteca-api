package com.exdemplo.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exdemplo.biblioteca.entity.Autor;
import com.exdemplo.biblioteca.exception.ResourceNotFoundException;
import com.exdemplo.biblioteca.repository.AutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    @Transactional(readOnly = true)
    public List<Autor> listar() {
        return (List<Autor>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Autor", id));
    }

    @Transactional
    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    @Transactional
    public Autor atualizar(Long id, Autor autor) {

        Autor autorExistente = buscarPorId(id);

        autorExistente.setNome(autor.getNome());
        autorExistente.setNacionalidade(autor.getNacionalidade());

        return repository.save(autorExistente);
    }

    @Transactional
    public void deletar(Long id) {

        Autor autor = buscarPorId(id);

        repository.delete(autor);
    }
}