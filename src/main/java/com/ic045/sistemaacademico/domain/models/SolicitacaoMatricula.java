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
    private Aluno aluno;

    @ManyToOne
    private OportunidadeMatricula oportunidadeMatricula;

    @OneToMany(mappedBy = "solicitacaoMatricula")
    private List<SolicitacaoTurma> solicitacaoTurmas;

    @Enumerated(EnumType.STRING)
    private Role.Status status;
}
