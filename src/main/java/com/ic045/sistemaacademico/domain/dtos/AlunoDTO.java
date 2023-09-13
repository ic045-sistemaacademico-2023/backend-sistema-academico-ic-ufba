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
public class AlunoDTO {

    private int id;

    private int idCurso;
    private int idTurma;
    private int idUsuario;
    private String nome;
    private int cr;

    @ToString.Exclude
    private CursoDTO curso;

    @ToString.Exclude
    private TurmaDTO turma;

}
