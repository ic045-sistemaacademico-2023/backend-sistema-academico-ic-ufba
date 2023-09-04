package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aluno")
public class Aluno {
    @Id
    private String id;

    private Curso curso;

    private Turma turma;

    private Usuario usuario;
    
    private double cr;
}
