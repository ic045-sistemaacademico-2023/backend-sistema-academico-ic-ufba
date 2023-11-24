package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@Entity
@Table(name = "solicitacao_matricula")
public class SolicitacaoMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "solicitacaoMatricula")
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "solicitacaoMatricula")
    private List<Turma> turmas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_oportunidade_matricula")
    private OpMatriculaDisciplinaTurma oportunidadeMatricula;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Role.Status status;
}