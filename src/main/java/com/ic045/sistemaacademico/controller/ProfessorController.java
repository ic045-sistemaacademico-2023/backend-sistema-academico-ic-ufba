package com.ic045.sistemaacademico.controller;

import java.util.List;

import com.ic045.sistemaacademico.controller.vos.request.InsertProfessorRequest;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	@Autowired
	private ProfessorService service;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/{id}")
	public ResponseEntity<Professor> findById(@PathVariable Long id) {
		Professor professor = service.findById(id);

		return professor != null ? ResponseEntity.ok(professor) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<Turma>> findAllTurmasByProfessorId(@PathVariable Long id) {
		List<Turma> turmas = service.findAllByProfessorId(id);

		return turmas != null ? ResponseEntity.ok(turmas) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<Professor>> findAllProfessors() {
    List<Professor> professors = service.findAllProfessors();

    return professors != null && !professors.isEmpty()
            ? ResponseEntity.ok(professors)
            : ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> postProfessor(@RequestBody InsertProfessorRequest professorRequest){
		Professor professor = new Professor();
		Usuario usuario = usuarioService.findById(professorRequest.usuario());
		professor.setUsuario(usuario);
		professor.setNome(professorRequest.nome());
		professor.setEmail(professorRequest.email());
		professor.setSenha(professorRequest.senha());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insertProfessor(professor));
	}
}
