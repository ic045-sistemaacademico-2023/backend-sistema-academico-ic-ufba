package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "aluno_turma")
public class AlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    private Aluno aluno;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_turma", referencedColumnName = "id")
    private Turma turma;
}
