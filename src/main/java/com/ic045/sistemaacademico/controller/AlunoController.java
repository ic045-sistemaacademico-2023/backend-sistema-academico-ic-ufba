package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.AlunoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno aluno = service.findById(id);

        return aluno != null ? ResponseEntity.ok(aluno): ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<Turma>> findAllTurmasByAlunoId(@PathVariable Long id) {
        List<Turma> turmas = service.findAllByAlunoId(id);

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }

}
