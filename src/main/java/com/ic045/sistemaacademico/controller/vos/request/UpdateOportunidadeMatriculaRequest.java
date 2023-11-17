package com.ic045.sistemaacademico.controller.vos.request;

public record UpdateOportunidadeMatriculaRequest(String nome, String descricao, String dataInicial, String dataFinal, Boolean aberta) {
}
