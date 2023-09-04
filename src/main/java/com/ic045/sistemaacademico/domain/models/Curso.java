package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Curso")
public class Curso {
    @Id
    private String id;

    private CoordenadorDeCurso coordenador;

    private String turno;
}
