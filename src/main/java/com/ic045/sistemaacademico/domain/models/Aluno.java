package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "aluno_turma",
        joinColumns = @JoinColumn(name = "id_aluno"),
        inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private Set<Turma> turmas;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cr")
    private int cr;
}
