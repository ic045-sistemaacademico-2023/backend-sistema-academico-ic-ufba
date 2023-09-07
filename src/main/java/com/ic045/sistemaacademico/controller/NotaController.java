package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota nota = service.findById(id);

        return nota != null ? ResponseEntity.ok(nota): ResponseEntity.notFound().build();
    }
}
