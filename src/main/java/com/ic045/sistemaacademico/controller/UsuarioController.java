package com.ic045.sistemaacademico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.services.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.findById(id);

		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Usuario> findByCpf(@PathVariable String cpf) {
		Usuario usuario = service.findByCpf(cpf);

		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
}
