package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.NotaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateNotaRequest;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota nota = service.findById(id);

        return nota != null ? ResponseEntity.ok(nota): ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Nota> insertNota(@RequestBody InsertNotaRequest insertNotaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertNotaData(insertNotaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nota> deleteNota(@PathVariable Long id) {
        service.deleteNota(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @RequestBody UpdateNotaRequest request) {
        Nota nota = service.updateNota(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }
}
