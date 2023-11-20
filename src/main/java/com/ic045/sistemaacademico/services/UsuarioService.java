package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.*;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CoordenadorDeCursoService coordenadorDeCursoService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private AlunoService alunoService;

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
        Role.Status status = Role.Status.WAITING_APPROVAL;

        if (
                request.getRole().equals(Role.Type.COORDENADOR_DE_CURSO) ||
                request.getRole().equals(Role.Type.PROFESSOR) ||
                request.getRole().equals(Role.Type.ADMIN)
        ) {
            status = Role.Status.APPROVED;
        }

        request.setSenha(encryptPassword(request.getSenha()));
        request.setStatus(status);

        Usuario usuario = repository.save(request);

        if (request.getRole().equals(Role.Type.PROFESSOR)) {
            Professor professor = new Professor();
            professor.setUsuario(usuario);
            professor.setNome(request.getNome());
            professor.setEmail(request.getEmail());

            professorService.insertProfessor(professor);
        } else if (request.getRole().equals(Role.Type.COORDENADOR_DE_CURSO)) {
            CoordenadorDeCurso coord = new CoordenadorDeCurso();
            coord.setUsuario(usuario);
            coord.setNome(request.getNome());
            coord.setEmail(request.getEmail());

            coordenadorDeCursoService.insertCoordenador(coord);
        } else if (request.getRole().equals(Role.Type.ADMIN)) {
            Administrador admin = new Administrador();
            admin.setUsuario(usuario);
            admin.setNome(request.getNome());
            admin.setEmail(request.getEmail());

            administradorService.insertAdmin(admin);
        }

        return usuario;
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = findById(id);

        if (usuario.getRole().equals(Role.Type.PROFESSOR)) {
            professorService.deleteByUserId(usuario.getId());
        } else if (usuario.getRole().equals(Role.Type.COORDENADOR_DE_CURSO)) {
            coordenadorDeCursoService.deleteByUserId(usuario.getId());
        } else if (usuario.getRole().equals(Role.Type.ADMIN)) {
            administradorService.deleteByUserId(usuario.getId());
        } else if (usuario.getRole().equals(Role.Type.ALUNO)) {
            alunoService.deleteByUserId(usuario.getId());
        }

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

    public List<Usuario> getApprovedUsers() {
        return repository.findByStatus(Role.Status.APPROVED);
    }

    public List<Usuario> getWaitlist() {
        return repository.findByStatus(Role.Status.WAITING_APPROVAL);
    }

    public Usuario approveUser(Long id) {
        Usuario usuario = findById(id);
        usuario.setStatus(Role.Status.APPROVED);
        return repository.save(usuario);
    }

    public Usuario denyUser(Long id) {
        Usuario usuario = findById(id);
        usuario.setStatus(Role.Status.DENIED);
        return repository.save(usuario);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
