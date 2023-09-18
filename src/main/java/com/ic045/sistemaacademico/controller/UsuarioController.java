package com.ic045.sistemaacademico.controller;

import com.ic045.sistemaacademico.controller.vos.request.InsertUsuarioRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.services.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

    @PostMapping(path = "/")
    public ResponseEntity<Usuario> insertLetter(@RequestBody InsertUsuarioRequest request) {
        Usuario usuario = service.insertUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.findById(id);

		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = service.findAll();

		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}
}
