package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CoordenadorDeCurso")
public class CoordenadorDeCurso {
    @Id
    private String id;

    private Usuario usuario;
}
