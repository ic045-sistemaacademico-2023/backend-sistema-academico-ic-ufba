package com.ic045.sistemaacademico.controller;

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

import com.ic045.sistemaacademico.controller.vos.request.InsertCursoRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateCursoRequest;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.services.CursoService;
@RestController
@RequestMapping("/curso")
public class CursoController {
	@Autowired
	private CursoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Curso> findById(@PathVariable Long id) {
		Curso curso = service.findById(id);

		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<?> InsertCurso(@RequestBody InsertCursoRequest ICS) {
		CoordenadorDeCurso CC = new CoordenadorDeCurso();
		CC.setId(ICS.coordenador());
		Curso curso = new Curso(CC, ICS.nome(),Integer.parseInt(ICS.semestre()), ICS.turno());
		curso.setPeriodo_curriculo("2023.2");

		return service.InsertCursoData(curso)
				? ResponseEntity.status(HttpStatus.CREATED).build()
				: ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
		service.deleteCurso(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody UpdateCursoRequest request) {
		Curso curso = service.updateCurso(id, request);

		return ResponseEntity.status(HttpStatus.OK).body(curso);
	}
}
