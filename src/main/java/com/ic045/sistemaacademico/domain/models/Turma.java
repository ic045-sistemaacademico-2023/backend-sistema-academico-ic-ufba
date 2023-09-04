package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Turma")
public class Turma {
    @Id
    private String id;

    private Disciplina disciplina;

    private Professor professor;

    private String dias;

    private String horario;
}
