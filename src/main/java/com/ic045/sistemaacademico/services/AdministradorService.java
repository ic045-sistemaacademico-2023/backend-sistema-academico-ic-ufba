package com.ic045.sistemaacademico.services;

import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Administrador;
import com.ic045.sistemaacademico.repositories.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository repository;

    public Administrador findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Administrador", id)));
    }

    public Administrador insertAdmin(Administrador admin) {
    	return repository.save(admin);
    }

    public void deleteByUserId(Long id) {
        Administrador admin = repository.findByUsuarioId(id);

        if (admin != null) {
            repository.delete(admin);
        }
    }
}
