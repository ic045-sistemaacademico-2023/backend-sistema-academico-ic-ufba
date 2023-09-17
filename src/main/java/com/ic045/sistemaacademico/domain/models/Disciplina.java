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
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_curso",referencedColumnName = "id")
    private Curso curso;

    private String nome;

    private String codigo;

    private String ementa;

    @Column(name = "pre_requisitos")
    private String preRequisitos;

    private String nivel;

    private String area;

    private String observacao;

    private int ch;

}
