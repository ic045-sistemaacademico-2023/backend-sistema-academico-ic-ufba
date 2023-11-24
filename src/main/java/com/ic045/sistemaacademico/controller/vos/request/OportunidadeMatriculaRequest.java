package com.ic045.sistemaacademico.controller.vos.request;

public record OportunidadeMatriculaRequest(String nome, String descricao, String dataInicial, String dataFinal,
		Boolean aberta, Long coordenador, DisciplinaTurmas[] disciplinaTurmas) {
}
