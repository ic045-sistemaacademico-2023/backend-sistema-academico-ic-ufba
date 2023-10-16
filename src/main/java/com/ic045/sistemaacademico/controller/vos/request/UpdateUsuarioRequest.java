package com.ic045.sistemaacademico.controller.vos.request;

// role e status na vdd são ENUM no MySQL, por isso, ocorre um erro em UsuarioService.java. Precisa resolver essa questão do 'ENUM' no Java
public record UpdateUsuarioRequest(
        String cpf,
        String senha,
        String email,
        String role,
        String status,
        String nome
) {
}