package com.ic045.sistemaacademico.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_solicitacao_matricula")
    private SolicitacaoMatricula solicitacaoMatricula;

    @NonNull
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "aluno_turma",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private Set<Turma> turmas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    private Set<Nota> notas;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;

    @NonNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "cr")
    private int cr;

    @Column(name = "periodo_ingresso")
    private String periodo_ingresso;
    
    @Column(name = "numero_matricula", unique = true)
    private String numeroMatricula;
}
