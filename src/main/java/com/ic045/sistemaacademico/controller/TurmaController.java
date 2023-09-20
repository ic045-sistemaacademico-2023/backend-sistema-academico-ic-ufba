package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/")
    public ResponseEntity<List<Turma>> findForSemestre(@RequestParam String period){
        List<Turma> turmas = service.findForSemestreData(period);

        return !turmas.isEmpty() ? ResponseEntity.ok(turmas):ResponseEntity.notFound().build();
    }
}
