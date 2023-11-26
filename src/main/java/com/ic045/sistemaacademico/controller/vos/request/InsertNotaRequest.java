package com.ic045.sistemaacademico.controller.vos.request;

public record InsertNotaRequest(
        Long aluno,
        Long turma,
        Long disciplina,
        Double nota,
        Long faltas
) {



}
