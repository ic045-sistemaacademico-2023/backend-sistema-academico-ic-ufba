package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Role;

public record InsertDisciplinaRequest(

        Long curso,
        String nome,
        String ementa,
        Role.Area area,
        String observacao,
        int ch,
        int chPratica,
        int chTeorica,
        int semestre

){
}
