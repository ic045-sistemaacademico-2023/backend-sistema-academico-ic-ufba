package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public Turma findById(Long id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Set<Turma> findAllByAluno(Long idAluno) {
        return repository.findAllByAluno(idAluno);
    }
}
