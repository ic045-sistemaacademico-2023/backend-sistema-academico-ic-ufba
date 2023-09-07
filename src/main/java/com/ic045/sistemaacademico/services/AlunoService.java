package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.repositories.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public Aluno findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
