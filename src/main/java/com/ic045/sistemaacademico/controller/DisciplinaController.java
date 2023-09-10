package com.ic045.sistemaacademico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	// TODO Create the endpoints to get by user
	// http://localhost:8080/sistemaacademico/disciplina/1
}
