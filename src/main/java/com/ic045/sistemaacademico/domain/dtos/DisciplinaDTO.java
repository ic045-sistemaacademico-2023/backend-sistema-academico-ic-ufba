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
public class DisciplinaDTO {

    private int idCurso;
    private String nome;
    private String codigo;
    private String ementa;
    private String preRequisitos;
    private String nivel;
    private String area;
    private String observacao;
    private int ch;

    @ToString.Exclude
    private CursoDTO curso;

}
