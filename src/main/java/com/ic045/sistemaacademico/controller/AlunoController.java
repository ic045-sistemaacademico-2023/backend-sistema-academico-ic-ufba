package com.ic045.sistemaacademico.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.controller.vos.request.InsertAlunoRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.services.AlunoService;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import com.ic045.sistemaacademico.utils.helpers.DateConverter;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService service;
    

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno aluno = service.findByUsuarioId(id);

        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/bycurso/{cursoId}")
    public ResponseEntity<List<Aluno>> findByCursoId(@PathVariable Long cursoId) {
    	List<Aluno> alunos = service.findByCursoId(cursoId);
        return alunos != null ? ResponseEntity.ok(alunos) : ResponseEntity.notFound().build();
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
    public ResponseEntity<Boolean> InsertAluno(@RequestBody InsertAlunoRequest InsertAluno) {
        Usuario user = new Usuario();
        Curso curso = new Curso();
        if (InsertAluno.usuario() == null || InsertAluno.curso() == null || InsertAluno.nome() == null)
            throw new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());
        user.setId(InsertAluno.usuario());
        curso.setId(InsertAluno.curso());
        Aluno aluno = new Aluno(user, InsertAluno.nome());
        aluno.setCurso(curso);
        aluno.setCr(0);
        aluno.setPeriodo_ingresso(DateConverter.getAnoPontoSemestre(LocalDateTime.now()));
        aluno.setNumeroMatricula(service.registrationNumber(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertAlunoData(aluno));
    }

}
