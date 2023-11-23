package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record UpdateTurmaRequest(
        Long disciplina,
        Long professor,
        String dias,
        String horario,
        Role.Sala sala,
        String semestre
) {
}

