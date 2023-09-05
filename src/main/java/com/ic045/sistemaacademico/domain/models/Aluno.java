package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_turma",referencedColumnName = "id")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "id_curso",referencedColumnName = "id")
    private Curso curso;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cr")
    private int cr;

}
