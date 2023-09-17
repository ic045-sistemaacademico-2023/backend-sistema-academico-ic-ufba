package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.controller.vos.request.InsertUsuarioRequest;
import com.ic045.sistemaacademico.utils.helpers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;

import java.util.NoSuchElementException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Long id) {
        try {
            return repository.findById(id).get() ;
        }catch (NoSuchElementException e){
            return  null;
        }
    }

    public Usuario insertUsuario(InsertUsuarioRequest request) {
        Usuario usuario = UsuarioMapper.mapInsertUsuarioRequestToUsuario(request);

        return repository.save(usuario);
    }
}



