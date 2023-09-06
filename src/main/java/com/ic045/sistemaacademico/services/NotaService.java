package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.repositories.NotaRepository;

@Service
public class NotaService {
    private NotaRepository repository;

    public Nota findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
