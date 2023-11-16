package com.ic045.sistemaacademico.controller;

import java.util.List;

import com.ic045.sistemaacademico.controller.vos.request.InsertDisciplinaRequest;
import com.ic045.sistemaacademico.domain.models.Curso;
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
		Disciplina disciplina = new Disciplina(null, curso, insertDisciplina.nome(),
				"", insertDisciplina.ementa(), insertDisciplina.preRequisitos(),
				insertDisciplina.area().name(), insertDisciplina.observacao(), insertDisciplina.chTotal(), insertDisciplina.chTeorica(),
				insertDisciplina.chPratica(), insertDisciplina.bibliografia());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.InsertDisciplinaData(disciplina));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boolean> editDisciplina(@PathVariable Long id, @RequestBody InsertDisciplinaRequest updatedDisciplina) {
			Curso curso = new Curso();
			curso.setId(updatedDisciplina.curso());

			Disciplina updatedDisciplinaModel = new Disciplina(null, curso, updatedDisciplina.nome(), "", updatedDisciplina.ementa(), updatedDisciplina.preRequisitos(),
							updatedDisciplina.area().name(), updatedDisciplina.observacao(), updatedDisciplina.chTotal(),
							updatedDisciplina.chTeorica(), updatedDisciplina.chPratica(), updatedDisciplina.bibliografia());

			boolean edited = service.editDisciplina(id, updatedDisciplinaModel);

			if (edited) {
					return ResponseEntity.ok(true);
			} else {
					return ResponseEntity.notFound().build();
			}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
	    service.deleteDisciplina(id);

	    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
	
	@GetMapping("/codigo/{cod}")
	public ResponseEntity<Disciplina> findByCodigo(@PathVariable String cod){
		Disciplina disciplina = service.findByCodigo(cod);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.notFound().build();
	}
}
