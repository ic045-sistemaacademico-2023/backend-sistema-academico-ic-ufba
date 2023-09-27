package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import com.ic045.sistemaacademico.domain.models.Role;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario findById(Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id)));
	}

	public Usuario findByCpf(String cpf) {
		return repository
				.findByCpf(cpf).get();
	}
	public List<Usuario> findAll() {
		try {
			List<Usuario> usuarios = repository.findAll();
			return usuarios;
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public Usuario insertUsuario(Usuario request) {
		request.setStatus(Role.Status.EMAIL_CHECK);

        return repository.save(request);
    }
}
