package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso curso = service.findById(id);

        return curso != null ? ResponseEntity.ok(curso): ResponseEntity.notFound().build();
    }
}
