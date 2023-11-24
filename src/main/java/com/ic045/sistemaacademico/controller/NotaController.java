package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.UpdateNotaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.services.NotaService;


@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService service;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota nota = service.findById(id);

        return nota != null ? ResponseEntity.ok(nota): ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/aluno")
    public ResponseEntity<Aluno> findAlunoByNota(@PathVariable Long id){
        Nota nota = service.findById(id);
        Aluno aluno = nota.getAluno();
        return aluno != null ? ResponseEntity.ok(aluno): ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Nota> insertNota(@RequestBody InsertNotaRequest insertNotaRequest){
    	if(insertNotaRequest.nota() == null)
    		return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertNotaData(insertNotaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nota> deleteNota(@PathVariable Long id) {
        service.deleteNota(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @RequestBody InsertNotaRequest request) {
        Nota nota = service.updateNota(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }

    @PutMapping("/{id}/disciplina/notasefaltas")
    public ResponseEntity<Nota> updateNotaeFaltas(@PathVariable Long id, @RequestBody UpdateNotaRequest request) {
        Nota nota = service.updateNotaeFalta(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }

}
