package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.projections.TurmaProjection;
import com.ic045.sistemaacademico.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public Professor findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public List<TurmaProjection> findAllByProfessorId(Long professorId) {
        return repository.findAllTurmasByProfessorId(professorId);
    }
}
