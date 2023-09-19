package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public Turma findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id)));
    }
}
