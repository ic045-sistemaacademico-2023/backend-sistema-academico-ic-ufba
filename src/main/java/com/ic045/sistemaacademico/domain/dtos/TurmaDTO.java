package com.ic045.sistemaacademico.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TurmaDTO {

    private int idDisciplina;
    private int idProfessor;
    private String dias;
    private String horario;

    @ToString.Exclude
    private DisciplinaDTO disciplina;

    @ToString.Exclude
    private ProfessorDTO professor;

}
