package com.ic045.sistemaacademico.domain.dtos;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TurmaDTO {
    private Long id;
    private String dias;
    private String horario;
    private String local;
    private String semestre;

    @ToString.Exclude
    private DisciplinaDTO disciplina;

    @ToString.Exclude
    private ProfessorDTO professor;

}
