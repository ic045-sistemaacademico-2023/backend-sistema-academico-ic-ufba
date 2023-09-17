package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record InsertUsuarioRequest(
    String nome,
    String cpf,
    String email,
    String senha,
    Role role
) {}
