package com.ic045.sistemaacademico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	@Autowired
	private ProfessorService service;

	@GetMapping("/{id}")
	public ResponseEntity<Professor> findById(@PathVariable Long id) {
		Professor professor = service.findByUserId(id);

		return professor != null ? ResponseEntity.ok(professor) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<Turma>> findAllTurmasByProfessorId(@PathVariable Long id) {
		List<Turma> turmas = service.findAllByUserId(id);

		return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<Professor>> findAllProfessors() {
    List<Professor> professors = service.findAllProfessors();

    return professors != null && !professors.isEmpty()
            ? ResponseEntity.ok(professors)
            : ResponseEntity.notFound().build();
	}
}
