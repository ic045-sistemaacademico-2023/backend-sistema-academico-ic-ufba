package com.ic045.sistemaacademico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.services.UsuarioService;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id) {
        Usuario usuario = service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
