package com.ic045.sistemaacademico.services;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.InsertUsuarioRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.Turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario findById(Long id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
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


	public void deleteUsuario(Long id) {
        Usuario usuario = findById(id);

        repository.delete(usuario);
    }


	public Usuario updateUsuario(Long id, UpdateUsuarioRequest request) {
        Usuario usuarioToUpdate = findById(id);

        usuarioToUpdate.setCpf(request.cpf());
        usuarioToUpdate.setSenha(request.senha());
        usuarioToUpdate.setEmail(request.email());
        usuarioToUpdate.setRole(request.role());  // Problema explicado no arquivo UpdateUsuarioResquest.java
		usuarioToUpdate.UserStatus(request.role()); // Problema explicado no arquivo UpdateUsuarioResquest.java
		usuarioToUpdate.setNome(request.nome());

        return repository.save(usuarioToUpdate);

	}


	
}
