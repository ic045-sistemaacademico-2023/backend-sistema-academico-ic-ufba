package com.ic045.sistemaacademico.domain.dtos;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
@Data/*Data devia puxar o RequiredArgs, mas não ta indo*/
public class TurmaDTO {
    @NonNull
    private Long id;
    @NonNull
    private String dias;
    @NonNull
    private String horario;
    private String local;
    private String semestre;
    @NonNull
    private String disciplinaNome;

    @ToString.Exclude
    private DisciplinaDTO disciplina;

    @ToString.Exclude
    private ProfessorDTO professor;

}
