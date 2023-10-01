package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
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
    public ResponseEntity<Boolean> InsertTurma(@RequestBody InsertTurmaRequest InsertTurma){
        Disciplina disciplina = new Disciplina();
        Professor professor = new Professor();
        Turma turma;
        disciplina.setId(InsertTurma.disciplina());
        String data = Arrays.stream(InsertTurma.dias()).map(Role.Date::getCodeDate).collect(Collectors.joining(","));

        if (InsertTurma.professor() != null) {
            professor.setId(InsertTurma.professor());
            turma = new Turma(disciplina, professor, data, InsertTurma.horario(), InsertTurma.local(), InsertTurma.semestre());
        }else {

            turma = new Turma(disciplina,data, InsertTurma.horario(), InsertTurma.local(), InsertTurma.semestre());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertTurmaData(turma));
    }
    @GetMapping("/aluno")
    public ResponseEntity<List<Turma>> findAllTurmasByAlunoId(@RequestParam Long id) {
        List<Turma> turmas = service.findTurmasByAlunoId(id);

        return turmas != null ? ResponseEntity.ok(turmas): ResponseEntity.notFound().build();
    }

}
