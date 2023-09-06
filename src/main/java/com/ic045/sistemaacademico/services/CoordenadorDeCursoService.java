package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.repositories.CoordenadorDeCursoRepository;

@Service
public class CoordenadorDeCursoService {
    private CoordenadorDeCursoRepository repository;

    public CoordenadorDeCurso findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
