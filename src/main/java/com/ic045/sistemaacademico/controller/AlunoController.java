package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertAlunoRequest;
import com.ic045.sistemaacademico.domain.models.*;
import com.ic045.sistemaacademico.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno aluno = service.findById(id);

        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/{id}/disciplinas/ativas")
    public ResponseEntity<Set<Disciplina>> findDisciplinas(@PathVariable Long id) {
        Aluno aluno = service.findById(id);
        Set<Turma> turmas = aluno.getTurmas();
        Set<Disciplina> disciplinas = new HashSet<>();
        turmas.forEach(turma -> disciplinas.add(turma.getDisciplina()));

        return disciplinas != null ? ResponseEntity.ok(disciplinas) : ResponseEntity.notFound().build();
    }
    @PostMapping("/")
    public ResponseEntity<Boolean> InsertAluno(@RequestBody InsertAlunoRequest InsertAluno){
        Usuario user = new Usuario();
        Curso curso = new Curso();
        user.setId(InsertAluno.usuario());
        curso.setId(InsertAluno.curso());
        Aluno aluno = new Aluno(user,curso, InsertAluno.nome());
        return service.InsertAlunoData(aluno)?
                ResponseEntity.status(HttpStatus.CREATED).build():ResponseEntity.badRequest().build();
    }

}
