package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateTurmaRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/semestre/{nrsemestre}")
    public ResponseEntity<List<Turma>> findForSemestre(@PathVariable String nrsemestre){
        List<Turma> turmas = service.findForSemestreData(nrsemestre);

        return !turmas.isEmpty() ? ResponseEntity.ok(turmas):ResponseEntity.notFound().build();
    }
    
    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<Turma>> findAllTurmasByAlunoId(@PathVariable Long id) {
        List<Turma> turmas = service.findTurmasByAlunoId(id);

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Turma>> findAllTurmas() {
        List<Turma> turmas = service.findAll();

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }
    
    @GetMapping("/disciplina/{disciplinaid}")
    public ResponseEntity<List<Turma>> findTurmasBydisciplinaId(@PathVariable Long disciplinaid) {
        List<Turma> turmas = service.findByDisciplinaId(disciplinaid);

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }
    
    @GetMapping("/disponiveismatricula")
    public ResponseEntity<List<Turma>> findTurmasDisponiveisMatricula() {
        List<Turma> turmas = service.findTurmasDisponiveisMatricula();
        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }
    
    @GetMapping("/salas")
    public EnumSet<Role.Sala> findAllSalas() {
        return EnumSet.allOf(Role.Sala.class);
    }
    
    @PostMapping("/")
    public ResponseEntity<Turma> insertTurma(@RequestBody InsertTurmaRequest insertTurmaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertTurmaData(insertTurmaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        service.deleteTurma(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable Long id, @RequestBody UpdateTurmaRequest request) {
        Turma turma = service.updateTurma(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(turma);
    }

}






