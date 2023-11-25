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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_oportunidade_matricula")
    private OportunidadeMatricula oportunidadeMatricula;

    @Enumerated(EnumType.STRING)
    private Role.Status status;

    public SolicitacaoMatricula() {
        this.status = Role.Status.WAITING_APPROVAL;
    }
}
