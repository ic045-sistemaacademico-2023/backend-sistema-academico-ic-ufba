package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import com.ic045.sistemaacademico.utils.helpers.DateConverter;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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

    public Aluno findByUsuarioId(Long id) {
        try {
            return repository
                    .findByUsuarioId(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Aluno", id));
        }
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

	public String registrationNumber(LocalDateTime now) {
		Random aleatorio = new Random();
		int value = aleatorio.nextInt(100001, 1000000);
		String ano = DateConverter.getAnoPontoSemestre(now);
		String numero_matricula = ano.substring(0, 4).concat(ano.substring(5, 6)).concat(Integer.toString(value));
		return numero_matricula;
		
	}
    
    public String checkSemester(int month) {
        return month >= 6 ? "2" : "1";
    }

    public void deleteByUserId(Long id) {
        Aluno aluno = repository.findByUsuarioId(id);

        if (aluno != null) {
            repository.delete(aluno);
        }
    }
}
