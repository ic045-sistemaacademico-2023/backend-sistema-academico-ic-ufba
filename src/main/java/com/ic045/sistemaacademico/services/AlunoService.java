package com.ic045.sistemaacademico.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.controller.vos.request.InsertAlunoRequest;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Aluno;
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

    public Boolean InsertAlunoData(Aluno insertAluno) {
       try {
           System.out.println(insertAluno.toString());
           if (repository.exists(Example.of(insertAluno))) {
            return false;
           }
            repository.save(insertAluno);
            return true;
       }catch (IllegalArgumentException | OptimisticLockingFailureException e){
           return false;
       }

    }
}
