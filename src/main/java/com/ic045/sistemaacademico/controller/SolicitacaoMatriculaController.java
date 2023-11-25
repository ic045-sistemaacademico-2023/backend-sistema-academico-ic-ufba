package com.ic045.sistemaacademico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;
import com.ic045.sistemaacademico.services.SolicitacaoMatriculaService;


@RestController
@RequestMapping("/solicitacao-matricula")
public class SolicitacaoMatriculaController {

    @Autowired
    private SolicitacaoMatriculaService service;

    @GetMapping
    public List<SolicitacaoMatricula> getAllSolicitacoesMatricula() {
        return service.getAllSolicitacoesMatricula();
    }

    @GetMapping("/{id}")
    public SolicitacaoMatricula getSolicitacaoMatriculaById(@PathVariable Long id) {
        return service.getSolicitacaoMatriculaById(id);
    }

    @PostMapping
    public SolicitacaoMatricula saveSolicitacaoMatricula(@RequestBody SolicitacaoMatricula solicitacaoMatricula) {
        return service.saveSolicitacaoMatricula(solicitacaoMatricula);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitacaoMatricula(@PathVariable Long id) {
        service.deleteSolicitacaoMatricula(id);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> verificarStatusMatricula(@PathVariable Long id) {
        String statusMatricula = service.verificarStatusMatricula(id);
        return new ResponseEntity<>(statusMatricula, HttpStatus.OK);
    }
}