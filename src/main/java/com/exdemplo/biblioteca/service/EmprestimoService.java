package com.exdemplo.biblioteca.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exdemplo.biblioteca.entity.Emprestimo;
import com.exdemplo.biblioteca.exception.ResourceNotFoundException;
import com.exdemplo.biblioteca.repository.EmprestimoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repository;

    private static final BigDecimal VALOR_MULTA_DIA = new BigDecimal("2.00");

    @Transactional(readOnly = true)
    public List<Emprestimo> listar() {
        return (List<Emprestimo>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Emprestimo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emprestimo", id));
    }

    @Transactional
    public Emprestimo salvar(Emprestimo emprestimo) {
        return repository.save(emprestimo);
    }

    @Transactional
    public Emprestimo atualizar(Long id, Emprestimo emprestimo) {
        Emprestimo existente = buscarPorId(id);

        existente.setDataEmprestimo(emprestimo.getDataEmprestimo());
        existente.setDataPrevistaDevolucao(emprestimo.getDataPrevistaDevolucao());
        existente.setDataDevolucao(emprestimo.getDataDevolucao());
        existente.setDevolvido(emprestimo.getDevolvido());
        existente.setLivro(emprestimo.getLivro());
        existente.setLeitor(emprestimo.getLeitor());

        return repository.save(existente);
    }

    @Transactional
    public Emprestimo registrarDevolucao(Long id) {
        Emprestimo emprestimo = buscarPorId(id);

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setDevolvido(true);

        return repository.save(emprestimo);
    }

    public BigDecimal calcularMulta(Emprestimo emprestimo) {
        if (emprestimo.getDataDevolucao() == null ||
                !emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
            return BigDecimal.ZERO;
        }

        long diasAtraso = ChronoUnit.DAYS.between(
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataDevolucao()
        );

        return VALOR_MULTA_DIA.multiply(BigDecimal.valueOf(diasAtraso));
    }

    @Transactional
    public void deletar(Long id) {
        Emprestimo emprestimo = buscarPorId(id);
        repository.delete(emprestimo);
    }
}