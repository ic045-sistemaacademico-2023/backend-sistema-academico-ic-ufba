package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertAlunoRequest;
import com.ic045.sistemaacademico.domain.models.*;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.services.AlunoService;
import com.ic045.sistemaacademico.services.NotaService;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import com.ic045.sistemaacademico.utils.helpers.DateConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @Autowired
    private NotaService notaService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno aluno = service.findByUsuarioId(id);

        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/disciplinas/ativas")
    public ResponseEntity<Set<Disciplina>> findDisciplinas(@PathVariable Long id) {
        Aluno aluno = service.findById(id);
        Set<Turma> turmas = aluno.getTurmas();
        Set<Disciplina> disciplinas = new HashSet<>();
        turmas.forEach(turma -> disciplinas.add(turma.getDisciplina()));
        return disciplinas != null ? ResponseEntity.ok(disciplinas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/notas")
    public ResponseEntity<Set<AlunoTurma>> findNotas(@PathVariable Long id) {
        Aluno aluno = service.findById(id);
        Set<Turma> turmas = aluno.getTurmas();
        Set<AlunoTurma> alunoTurmas = new HashSet<>();
        for (Turma turma : turmas) {
            Nota nota = notaService.findByAlunoAndTurma(aluno, turma);
            alunoTurmas.add(new AlunoTurma(aluno, turma, nota.getNota(), nota.getFaltas()));
        }
        return ResponseEntity.ok(alunoTurmas);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> InsertAluno(@RequestBody InsertAlunoRequest InsertAluno){
        Usuario user = new Usuario();
        Curso curso = new Curso();
        if (InsertAluno.usuario() == null || InsertAluno.curso() == null || InsertAluno.nome() == null) throw new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());
        user.setId(InsertAluno.usuario());
        curso.setId(InsertAluno.curso());
        Aluno aluno = new Aluno(user, InsertAluno.nome());
        aluno.setCurso(curso);
        aluno.setCr(0);
        aluno.setPeriodo_ingresso(DateConverter.getAnoPontoSemestre(LocalDateTime.now()));
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.InsertAlunoData(aluno));
    }

}
