package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;


public record UpdateUsuarioRequest(
        String cpf,
        String senha,
        String email,
        Role.Type role,
        Role.Status status,
        String nome
) {
}