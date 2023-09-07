package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.services.CoordenadorDeCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorDeCursoController {
    @Autowired
    private CoordenadorDeCursoService service;

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDeCurso> findById(@PathVariable Long id) {
        CoordenadorDeCurso coordenadorDeCurso= service.findById(id);

         return coordenadorDeCurso != null ? ResponseEntity.ok(coordenadorDeCurso): ResponseEntity.notFound().build();
    }
}
