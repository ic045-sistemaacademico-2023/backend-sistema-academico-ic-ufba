package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.repositories.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public Boolean InsertCursoData(Curso curso){
        if (repository.exists(Example.of(curso))){return false;}
        try {
            repository.save(curso);
            return true;
        }catch (IllegalArgumentException e){
            return   false;
        }

    }
    public Curso findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }

}
