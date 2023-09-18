package com.ic045.sistemaacademico.controller.vos.request;


import com.ic045.sistemaacademico.domain.models.Role;

public record InsertCursoRequest(
        String nome,
        String semestre,
        Role.Shift turno,
        Long coordenador


) {
}
