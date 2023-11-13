package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;

public record UpdateTurmaRequest(
        Disciplina disciplina,
        Professor professor,
        String dias,
        String horario,
        String local,
        String semestre
) {
}

