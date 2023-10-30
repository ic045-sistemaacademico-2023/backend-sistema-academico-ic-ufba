package com.ic045.sistemaacademico.controller.vos.request;

public record InsertNotaRequest(
        Long id_aluno,
        Long id_turma,
        Double nota
) {



}
