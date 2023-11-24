package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@Table(name = "solicitacao_matricula")
public class SolicitacaoMatricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    List<SolicitacaoTurma> solicitacoesTurmas;

    @ManyToOne
    @JoinColumn(name = "id_oportunidade_matricula")
    private OpMatriculaDisciplinaTurma oportunidadeMatricula;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    private Role.Status status;

    // getters and setters
}
