package com.ic045.sistemaacademico.controller;

import java.util.List;

import com.ic045.sistemaacademico.controller.vos.request.InsertDisciplinaRequest;
import com.ic045.sistemaacademico.domain.models.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.controller.vos.request.InsertDisciplinaRequest;
import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
	@Autowired
	private DisciplinaService service;

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> findById(@PathVariable Long id) {
		Disciplina disciplina = service.findById(id);

		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.notFound().build();
	}

	@GetMapping("/curso/{id}")
	public ResponseEntity<List<Disciplina>> findAllByCursoId(@PathVariable Long id) {
		List<Disciplina> disciplinas = service.findAllByCursoId(id);

		return disciplinas != null ? ResponseEntity.ok(disciplinas) : ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> InsertDisciplina(@RequestBody InsertDisciplinaRequest insertDisciplina) {
		Curso curso = new Curso();
		curso.setId(insertDisciplina.curso());
		Disciplina disciplina = new Disciplina(insertDisciplina.curso(), curso, insertDisciplina.nome(),
				insertDisciplina.ementa(), insertDisciplina.requisitos(), insertDisciplina.area().name(),
				insertDisciplina.observacao(), null, insertDisciplina.chPratica(), insertDisciplina.chTeorica(),
				insertDisciplina.chTotal(), insertDisciplina.bibliografia());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertDisciplinaData(disciplina));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Disciplina>> findAllDisciplinas() {
	    List<Disciplina> disciplinas = service.findAllDisciplinas();

	    return disciplinas != null ? ResponseEntity.ok(disciplinas) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<Turma>> findAllTurmasByDisciplinaId(@PathVariable Long id) {
	    List<Turma> turmas = service.findAllByDisciplinaId(id);

	    return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
	}
}
