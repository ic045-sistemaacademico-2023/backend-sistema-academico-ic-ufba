package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_disciplina",referencedColumnName = "id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor",referencedColumnName = "id")
    private Professor professor;

    private String dias;

    private String horario;

    private String local;

    private String semestre;

}
