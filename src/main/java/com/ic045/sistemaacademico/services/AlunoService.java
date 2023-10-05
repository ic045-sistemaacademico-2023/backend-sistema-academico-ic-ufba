package com.ic045.sistemaacademico.services;
import java.util.List;



import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
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

    public Boolean InsertAlunoData(Aluno insertAluno)  {
       try {

           if (repository.existsByusuarioId(insertAluno.getUsuario().getId())) {
                throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
           }
            repository.save(insertAluno);
            return true;
       }catch (IllegalArgumentException e){
           throw new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());
       }catch (OptimisticLockingFailureException e){
           throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
       }
    }

}
