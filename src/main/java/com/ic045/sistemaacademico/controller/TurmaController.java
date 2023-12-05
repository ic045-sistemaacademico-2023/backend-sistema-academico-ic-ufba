package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateTurmaRequest;
import com.ic045.sistemaacademico.domain.models.*;
import com.ic045.sistemaacademico.domain.models.Role.Sala;
import com.ic045.sistemaacademico.services.AlunoService;
import com.ic045.sistemaacademico.services.SolicitacaoTurmaService;
import com.ic045.sistemaacademico.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private SolicitacaoTurmaService solicitacaoTurmaService;

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Long id) {
        Turma turma = service.findById(id);

        return turma != null ? ResponseEntity.ok(turma)
                : ResponseEntity
                        .notFound()
                        .build();
    }

    @GetMapping("/{id}/sala")
    public ResponseEntity<Sala> findSalaByIdTurma(@PathVariable Long id) {
        Turma turma = service.findById(id);
        Role.Sala sala = turma.getSala();

        return sala != null ? ResponseEntity.ok(sala)
                : ResponseEntity
                        .notFound()
                        .build();
    }

    @GetMapping("/semestre/{nrsemestre}")
    public ResponseEntity<List<Turma>> findForSemestre(@PathVariable String nrsemestre) {
        List<Turma> turmas = service.findForSemestreData(nrsemestre);

        return !turmas.isEmpty() ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<Turma>> findAllTurmasByUserId(@PathVariable Long id) {
        Aluno aluno = alunoService.findByUsuarioId(id);
        List<Turma> turmas = service.findTurmasByAlunoId(aluno.getId());

        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Turma>> findAllTurmas() {
        List<Turma> turmas = service.findAll();

        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/disciplina/{disciplinaid}")
    public ResponseEntity<List<Turma>> findTurmasBydisciplinaId(@PathVariable Long disciplinaid) {
        List<Turma> turmas = service.findByDisciplinaId(disciplinaid);

        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/disponiveismatricula")
    public ResponseEntity<List<Turma>> findTurmasDisponiveisMatricula() {
        List<Turma> turmas = service.findTurmasDisponiveisMatricula();
        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/disponiveis/coordenador/{coordenadorId}")
    public ResponseEntity<List<Turma>> findTurmasDisponiveisPorCursoId(@PathVariable Long coordenadorId) {
        List<Turma> turmas = service.findTurmasDisponiveisPorCoordenadorId(coordenadorId);
        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/salas")
    public EnumSet<Role.Sala> findAllSalas() {
        return EnumSet.allOf(Role.Sala.class);
    }

    @PostMapping("/")
    public ResponseEntity<Turma> insertTurma(@RequestBody InsertTurmaRequest insertTurmaRequest) {
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

    @PostMapping("/{turmaId}/adicionar-aluno/{alunoId}")
    public ResponseEntity<AlunoTurma> adicionarAlunoTurma(
            @PathVariable Long turmaId,
            @PathVariable Long alunoId) {

        AlunoTurma alunoTurma = service.adicionarAlunoTurma(turmaId, alunoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoTurma);
    }

    @GetMapping("/comprovante-solicitacao-matricula/{alunoId}")
    public ResponseEntity<List<SolicitacaoTurma>> findTurmasByAlunoId(@PathVariable Long alunoId) {
        List<SolicitacaoTurma> turmas = solicitacaoTurmaService.getTurmasByAlunoId(alunoId);

        return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
    }
}
