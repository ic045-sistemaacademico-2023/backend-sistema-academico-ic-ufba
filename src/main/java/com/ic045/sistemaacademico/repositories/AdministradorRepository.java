package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    Administrador findByUsuarioId(Long id);
}
