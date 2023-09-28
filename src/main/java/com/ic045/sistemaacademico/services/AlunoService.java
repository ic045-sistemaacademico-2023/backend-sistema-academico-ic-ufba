package com.ic045.sistemaacademico.services;
import java.util.List;

//import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public Aluno findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Aluno", id)));
    }

    public List<Turma> findAllByAlunoId(Long alunoId) {
        List<Turma> turmas = repository.findAllTurmasByAlunoId(alunoId);

        return turmas;
    }

}
