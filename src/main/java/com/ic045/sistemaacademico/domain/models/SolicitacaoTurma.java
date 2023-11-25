package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SolicitacaoTurma {
    @Id
    private Long id;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private SolicitacaoMatricula solicitacaoMatricula;

    @Enumerated(EnumType.STRING)
    private Role.Status status;
}
