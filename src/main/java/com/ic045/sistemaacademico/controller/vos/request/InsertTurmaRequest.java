package com.ic045.sistemaacademico.controller.vos.request;

public record InsertTurmaRequest(
        Long disciplina,
        Long professor,
        String dias,
        String horario,
        String local,
        String semestre
) {
}
