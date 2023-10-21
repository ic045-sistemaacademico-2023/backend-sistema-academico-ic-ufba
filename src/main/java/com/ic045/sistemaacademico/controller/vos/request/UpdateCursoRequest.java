package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record UpdateCursoRequest(String nome, int semestre, Role.Shift turno, Long coordenador) {
}
