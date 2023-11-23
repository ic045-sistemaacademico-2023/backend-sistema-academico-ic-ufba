package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record InsertTurmaRequest(
        Long disciplina,
        Long professor,
        Role.Date[] dias,
        String horario,
        Role.Sala sala,
        String semestre
) {
}
