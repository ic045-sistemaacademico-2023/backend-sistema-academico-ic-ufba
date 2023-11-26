package com.ic045.sistemaacademico.controller.vos.request;

public record InsertSolicitacaoMatriculaRequest (
        Long aluno,
        Long[] turmas
) {

}