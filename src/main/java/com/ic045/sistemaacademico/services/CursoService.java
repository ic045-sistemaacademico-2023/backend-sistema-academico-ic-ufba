package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.repositories.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public Curso findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
