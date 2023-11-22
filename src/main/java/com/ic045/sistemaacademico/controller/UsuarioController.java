package com.ic045.sistemaacademico.controller;

import java.util.List;

import com.ic045.sistemaacademico.controller.vos.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.security.JwtTokenProvider;
import com.ic045.sistemaacademico.services.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@Autowired
	private JwtTokenProvider provider;

	@PostMapping(path = "/")
	public ResponseEntity<Usuario> insertUsuario(@RequestBody Usuario usuario) {
		service.insertUsuario(usuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
		Usuario usuario = service.findById(id);
		UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getCpf(), usuario.getEmail(),
				usuario.getRole().toString(), usuario.getStatus().toString(), usuario.getNome());

		return ResponseEntity.ok(usuarioResponse);
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

	@GetMapping("/me")
	public ResponseEntity<UsuarioResponse> getCurrentUser(@RequestHeader("Authorization") String token) {

		// Ignora o "Bearer " no início do token
		String jwtToken = token.substring(7);

		DecodedJWT decodedJWT = provider.validateToken(jwtToken);
		Long userId = Long.parseLong(decodedJWT.getSubject());

		Usuario usuario = service.findById(userId);
		UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getCpf(), usuario.getEmail(),
				usuario.getRole().toString(), usuario.getStatus().toString(), usuario.getNome());

		return ResponseEntity.ok(usuarioResponse);
	}

	@GetMapping("/approved")
	public ResponseEntity<List<Usuario>> getApprovedUsers() {
		List<Usuario> usuarios = service.getApprovedUsers();
		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}

	@GetMapping("/waitlist")
	public ResponseEntity<List<Usuario>> getWaitlist() {
		List<Usuario> usuarios = service.getWaitlist();
		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}

	@PutMapping("/approve/{id}")
	public ResponseEntity<Usuario> approveUser(@PathVariable Long id) {
		Usuario usuario = service.approveUser(id);
		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/reprove/{id}")
	public ResponseEntity<Usuario> reproveUser(@PathVariable Long id) {
		Usuario usuario = service.denyUser(id);
		return ResponseEntity.ok(usuario);
	}
}
