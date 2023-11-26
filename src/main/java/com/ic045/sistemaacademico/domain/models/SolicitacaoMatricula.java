package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "solicitacao_matricula")
@Entity
public class SolicitacaoMatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_oportunidade_matricula")
    private OportunidadeMatricula oportunidadeMatricula;

    @Enumerated(EnumType.STRING)
    private Role.Status status;

    public SolicitacaoMatricula( Aluno aluno, OportunidadeMatricula oportunidadeMatricula ) {
        this.aluno = aluno;
        this.oportunidadeMatricula = oportunidadeMatricula;
        this.status = Role.Status.WAITING_APPROVAL;
    }

    public SolicitacaoMatricula() {

    }
}
