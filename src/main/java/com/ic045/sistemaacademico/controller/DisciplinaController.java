package com.ic045.sistemaacademico.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.controller.vos.request.InsertDisciplinaRequest;
import com.ic045.sistemaacademico.domain.models.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ic045.sistemaacademico.domain.models.Disciplina;
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

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Disciplina> findByCodigo(@PathVariable String codigo) {
		Disciplina disciplina = service.findByCodigo(codigo);

		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.notFound().build();
	}

	@GetMapping("/curso/{id}")
	public ResponseEntity<Map<Integer, List<Disciplina>>> findAllByCursoId(@PathVariable Long id) {
		Map<Integer, List<Disciplina>>  disciplinas = service.findAllByCursoId(id)
																													.stream()
																													.collect(Collectors.groupingBy(Disciplina::getSemestre));

		return disciplinas != null ? ResponseEntity.ok(disciplinas) : ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> InsertDisciplina(@RequestBody InsertDisciplinaRequest insertDisciplina){
		Curso curso = new Curso();
		curso.setId(insertDisciplina.curso());
		Disciplina disciplina = new Disciplina(curso
				,insertDisciplina.nome(),insertDisciplina.ementa()
				,insertDisciplina.requisitos(),insertDisciplina.area().name()
				,insertDisciplina.observacao(),insertDisciplina.ch(), insertDisciplina.semestre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertDisciplinaData(disciplina));
	}
}
