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
    private Long id;
    private String dias;
    private String horario;
    private String local;
    private String semestre;
    private String disciplinaNome;

    public TurmaDTO(Long id, String disciplinaNome, String dias, String horario) {
        this.id = id;
        this.disciplinaNome = disciplinaNome;
        this.dias = dias;
        this.horario = horario;
    }

    @ToString.Exclude
    private DisciplinaDTO disciplina;

    @ToString.Exclude
    private ProfessorDTO professor;

}
