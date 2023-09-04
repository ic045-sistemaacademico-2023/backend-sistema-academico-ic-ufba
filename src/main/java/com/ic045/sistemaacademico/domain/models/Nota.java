package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Nota")
public class Nota {
    @Id
    private String id;

    private Aluno aluno;

    private Turma turma;

    private double nota;

    private String semestre;
    
}
