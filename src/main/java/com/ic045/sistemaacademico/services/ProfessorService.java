package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public Professor findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Professor", id)));
    }

    public List<Turma> findAllByProfessorId(Long professorId) {
        List<Turma> turmas = repository.findAllTurmasByProfessorId(professorId);

        return turmas;
    }

    public Professor insertProfessor (Professor professor) {
    	return repository.save(professor);
    }

    public void deleteByUserId(Long id) {
        Professor professor = repository.findByUsuarioId(id);

        if (professor != null) {
            repository.delete(professor);
        }
    }

    public List<Professor> findAllProfessors() {
        return repository.findAll();
    }
}
