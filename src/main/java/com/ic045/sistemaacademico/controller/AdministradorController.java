package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Administrador;
import com.ic045.sistemaacademico.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdministradorController {
    @Autowired
    private AdministradorService service;

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> findById(@PathVariable Long id) {
        Administrador administrador = service.findById(id);

        return administrador != null ? ResponseEntity.ok(administrador): ResponseEntity.notFound().build();
    }
}
