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

    @GetMapping("/")
    public ResponseEntity<List<Turma>> findForSemestre(@RequestParam String period){
        List<Turma> turmas = service.findForSemestreData(period);

        return !turmas.isEmpty() ? ResponseEntity.ok(turmas):ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Turma> insertTurma(@RequestBody InsertTurmaRequest insertTurmaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertTurmaData(insertTurmaRequest));
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<Turma>> findAllTurmasByAlunoId(@RequestParam Long id) {
        List<Turma> turmas = service.findTurmasByAlunoId(id);

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
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
