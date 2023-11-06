package com.ic045.sistemaacademico.controller.vos.request;

public record InsertOportunidadeMatriculaRequest(String nome, String descricao, String dataInicial, String dataFinal, DisciplinaTurmas[] disciplinaTurmas) {
}
