package com.ic045.sistemaacademico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.services.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

    @PostMapping(path = "/")
    public ResponseEntity<Usuario> insertUsuario(@RequestBody Usuario usuario) {
        service.insertUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.findById(id);

		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	@GetMapping("/cpf")
	public ResponseEntity<Usuario> getUserByCpf(@RequestParam String cpf) {
		return ResponseEntity.ok(service.findByCpf(cpf));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = service.findAll();
		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody UpdateUsuarioRequest request) {
        Usuario usuario = service.updateUsuario(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }


}
