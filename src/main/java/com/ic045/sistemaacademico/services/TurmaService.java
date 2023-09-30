package com.ic045.sistemaacademico.services;

import java.util.List;

import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Example;
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
                .orElseThrow(
                        () -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id)));
    }

    public List<Turma> findForSemestreData(String period) {
        return repository.findBysemestre(period).get();
    }

//    public List<Turma> findAllByAluno(Long idAluno) {
//        return repository.findAllByAluno(idAluno);
//    }

    public Boolean InsertTurmaData(Turma turma) {
        try {
           if (repository.exists(Example.of(turma)))throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
           repository.save(turma);
            return true;
        }catch (IllegalArgumentException e){throw  new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());}
        catch (OptimisticLockingFailureException e){throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());}
    }
}
