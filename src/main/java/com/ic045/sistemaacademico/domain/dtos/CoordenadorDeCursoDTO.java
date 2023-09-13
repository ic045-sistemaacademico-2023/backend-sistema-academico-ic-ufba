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
public class CoordenadorDeCursoDTO {

    private int idUsuario;
    private String nome;
    private String email;
    private String senha;

}
