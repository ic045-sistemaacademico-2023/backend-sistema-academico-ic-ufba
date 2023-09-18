package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_coordenador",referencedColumnName = "id")
    private CoordenadorDeCurso coordenadorDeCurso;
    @NonNull
    private String nome;
    @NonNull
    private int semestre;
    @NonNull
    private Role.Shift turno;

}
