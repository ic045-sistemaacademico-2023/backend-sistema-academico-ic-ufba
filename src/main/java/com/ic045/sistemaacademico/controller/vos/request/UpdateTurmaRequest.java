package com.ic045.sistemaacademico.controller.vos.request;

public record UpdateTurmaRequest(
        Long disciplina,
        Long professor,
        String dias,
        String horario,
        String local,
        String semestre
) {
}

