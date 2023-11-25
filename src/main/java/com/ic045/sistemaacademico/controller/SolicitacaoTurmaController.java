package com.ic045.sistemaacademico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;
import com.ic045.sistemaacademico.services.SolicitacaoTurmaService;

import java.util.List;

@RestController
@RequestMapping("/solicitacao-turma")
public class SolicitacaoTurmaController {

    @Autowired
    private SolicitacaoTurmaService service;

    @GetMapping
    public List<SolicitacaoTurma> getAllSolicitacoesTurma() {
        return service.getAllSolicitacoesTurma();
    }

    @GetMapping("/{id}")
    public SolicitacaoTurma getSolicitacaoTurmaById(@PathVariable Long id) {
        return service.getSolicitacaoTurmaById(id);
    }

    @PostMapping
    public SolicitacaoTurma saveSolicitacaoTurma(@RequestBody SolicitacaoTurma solicitacaoTurma) {
        return service.saveSolicitacaoTurma(solicitacaoTurma);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitacaoTurma(@PathVariable Long id) {
        service.deleteSolicitacaoTurma(id);
    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity<Void> aprovarSolicitacaoTurma(@PathVariable Long id) {
        SolicitacaoTurma solicitacaoTurma = service.getSolicitacaoTurmaById(id);

        service.aprovarSolicitacaoTurma(solicitacaoTurma);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/recusar/{id}")
    public ResponseEntity<Void> recusarSolicitacaoTurma(@PathVariable Long id) {
        SolicitacaoTurma solicitacaoTurma = service.getSolicitacaoTurmaById(id);

        service.recusarSolicitacaoTurma(solicitacaoTurma);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
