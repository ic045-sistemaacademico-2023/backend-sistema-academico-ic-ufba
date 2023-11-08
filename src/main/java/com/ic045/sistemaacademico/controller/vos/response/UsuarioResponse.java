package com.ic045.sistemaacademico.controller.vos.response;

public record UsuarioResponse(
        Long id,
        String cpf,
        String email,
        String role,
        String status,
        String nome
) {
}
