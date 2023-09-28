package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_curso",referencedColumnName = "id")
    private Curso curso;
    @NonNull
    private String nome;

    private String codigo;
    @NonNull
    private String ementa;
    @NonNull
    @Column(name = "pre_requisitos")
    private String preRequisitos;
    @NonNull
    private String area;
    @NonNull
    private String observacao;
    @NonNull
    private int ch;

}
