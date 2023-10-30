package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;
import java.util.List;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.repositories.CoordenadorDeCursoRepository;

@Service
public class CoordenadorDeCursoService {
    @Autowired
    private CoordenadorDeCursoRepository repository;

    public CoordenadorDeCurso findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Coordenador de Curso", id)));
    }

    public List<CoordenadorDeCurso> findAll() {
        return repository.findAll();
    }
}
