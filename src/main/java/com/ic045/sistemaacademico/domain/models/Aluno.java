package com.ic045.sistemaacademico.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @NonNull
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id",unique = true)
    private Usuario usuario;
    @JsonIgnore
    @ManyToMany(mappedBy = "alunos")
    private Set<Turma> turmas;
    @NonNull
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
}
