package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "solicitacao_turma")
public class SolicitacaoTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_solicitacao_matricula")
    private SolicitacaoMatricula solicitacaoMatricula;

    @Enumerated(EnumType.STRING)
    private Role.Status status;

    public SolicitacaoTurma( SolicitacaoMatricula solicitacaoMatricula, Turma turma, Aluno aluno ) {
        this.solicitacaoMatricula = solicitacaoMatricula;
        this.turma = turma;
        this.aluno = aluno;
        this.status = Role.Status.WAITING_APPROVAL;
    }

    public SolicitacaoTurma() {
        
    }
}
