package com.ic045.sistemaacademico.domain.models;

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

//    @ManyToMany
//    @JoinTable(
//        name = "aluno_turma",
//        joinColumns = @JoinColumn(name = "id_aluno"),
//        inverseJoinColumns = @JoinColumn(name = "id_turma"))
//    private Set<Turma> turmas;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;
    @NonNull
    @Column(name = "nome")
    private String nome;
    @Column(name = "cr")
    private int cr;
}
