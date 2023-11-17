package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String code;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_disciplina", referencedColumnName = "id")
    private Disciplina disciplina;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_professor", referencedColumnName = "id")
    private Professor professor;

    @ManyToMany
    @JoinTable(
        name = "aluno_turma",
        joinColumns = @JoinColumn(name = "id_aluno"),
        inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private Set<Aluno> alunos;
    @NonNull
    private String dias;
    @NonNull
    private String horario;
    @NonNull
    private String local;
    @NonNull
    private String semestre;

    public Turma(@NonNull Disciplina disciplina, @NonNull Professor professor, @NonNull String dias, @NonNull String horario, @NonNull String local, @NonNull String semestre) {
    	this.disciplina = disciplina;
    	this.professor = professor;
    	this.dias = dias;
        this.horario = horario;
        this.local = local;
        this.semestre = semestre;
    }
}
