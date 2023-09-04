package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Disciplina")
public class Disciplina {
    @Id
    private String id;

    private Curso curso;

    private String nome;

    private String codigo;

    private String ementa;

    private String pre_requisitos;

    private String nivel;

    private String area;

    private String observacao;

    private int ch;
}
