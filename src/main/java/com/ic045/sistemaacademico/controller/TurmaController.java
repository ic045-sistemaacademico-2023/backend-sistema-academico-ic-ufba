package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService service;
    private Disciplina disciplina;
    private Professor professor;
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
        disciplina.setId(InsertTurma.disciplina());
        Turma turma;
        try {
            professor.setId(InsertTurma.professor());
             turma = new Turma(disciplina,professor,InsertTurma.dias(),InsertTurma.horario(), InsertTurma.local(), InsertTurma.semestre());
        }catch (NullPointerException e){
             turma = new Turma(disciplina,null,InsertTurma.dias(),InsertTurma.horario(), InsertTurma.local(), InsertTurma.semestre());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertTurmaData(turma));
    }
}
