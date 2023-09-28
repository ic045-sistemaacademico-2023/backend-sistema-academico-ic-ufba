package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record InsertDisciplinaRequest(

        Long curso,
        String nome,
        String ementa,
        String requisitos,
        Role.Area area,
        String observacao,
        int ch

){
}
