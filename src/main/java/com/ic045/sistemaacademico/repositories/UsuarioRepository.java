package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("SELECT t FROM Usuario t WHERE t.usuario.cpf = :usuarioCpf")
	Usuario findByCpf(@Param("usuarioCpf") String usuarioCpf);
}
