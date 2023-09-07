package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public Disciplina findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
