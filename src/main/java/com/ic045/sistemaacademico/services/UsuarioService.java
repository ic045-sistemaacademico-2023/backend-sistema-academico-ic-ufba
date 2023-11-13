package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Role;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario findById(Long id) {
        try {
            return repository
                    .findById(id)
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Usuario findByCpf(String cpf) {
        return repository
                .findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.USUARIO_CPF_NOT_FOUND.getMessage(), "Usuario", cpf)));
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
        request.setSenha(encryptPassword(request.getSenha()));

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
        usuarioToUpdate.setNome(request.nome());

        return repository.save(usuarioToUpdate);

    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
