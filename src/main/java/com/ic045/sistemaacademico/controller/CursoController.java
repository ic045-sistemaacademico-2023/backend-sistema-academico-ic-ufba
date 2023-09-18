package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertCursoRequest;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso curso = service.findById(id);

        return curso != null ? ResponseEntity.ok(curso): ResponseEntity.notFound().build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> InsertCurso(@RequestBody InsertCursoRequest ICS){
        CoordenadorDeCurso CC = new CoordenadorDeCurso();
        CC.setId(ICS.coordenador());
     return service.InsertCursoData(new Curso(CC,ICS.nome()
             ,Integer.parseInt(ICS.semestre()),ICS.turno()))?
             ResponseEntity.ok().build():ResponseEntity.badRequest().build();

    }
}
