package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Long id) {
        Turma turma = service.findById(id);

        return turma != null ?
                ResponseEntity.ok(turma) :
                ResponseEntity
                        .notFound()
                        .build();
    }
}
