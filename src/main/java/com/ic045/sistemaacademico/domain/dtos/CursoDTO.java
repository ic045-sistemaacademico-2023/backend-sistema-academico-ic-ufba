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
public class CursoDTO {

    private int idCoordenador;
    private String nome;
    private int semestre;
    private String turno;

    @ToString.Exclude
    private CoordenadorDeCursoDTO coordenador;

}

