package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Administrador;
import com.ic045.sistemaacademico.repositories.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository repository;

    public Administrador findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
