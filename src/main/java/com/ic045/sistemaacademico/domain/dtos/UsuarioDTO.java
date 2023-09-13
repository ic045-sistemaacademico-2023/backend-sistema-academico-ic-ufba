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
public class UsuarioDTO {
    private int id;

    private String cpf;
    private String senha;
    private String email;
    private String role;
    private String status;
    private String nome;

}
