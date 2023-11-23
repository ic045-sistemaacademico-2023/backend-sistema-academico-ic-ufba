package com.ic045.sistemaacademico.controller.vos.request;

public record UpdateNotaRequest(
        Long aluno,
        Long turma,
        Double nota,

        Double faltas
) {



}
