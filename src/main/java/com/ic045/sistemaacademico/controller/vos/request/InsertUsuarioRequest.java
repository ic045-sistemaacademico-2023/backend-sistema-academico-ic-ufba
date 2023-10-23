package com.ic045.sistemaacademico.controller.vos.request;

public record InsertUsuarioRequest(
        String cpf,
        String senha,
        String email,
        String role,
        String status,
        String nome
) {
}