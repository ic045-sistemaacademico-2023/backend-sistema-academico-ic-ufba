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
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", referencedColumnName = "id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor", referencedColumnName = "id")
    private Professor professor;

    @ManyToMany
    @JoinTable(
        name = "aluno_turma",
        joinColumns = @JoinColumn(name = "id_turma"),
        inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private Set<Aluno> alunos;

    private String dias;

    private String horario;

    private String local;

    private String semestre;
}
