package com.ic045.sistemaacademico.repositories;

import java.util.List;
import java.util.Optional;

import com.ic045.sistemaacademico.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByCpf(String usuarioCpf);

	List<Usuario> findByStatus(Role.Status status);
}
