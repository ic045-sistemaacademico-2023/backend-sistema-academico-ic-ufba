package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Role;

import java.util.List;

public record InsertDisciplinaRequest(
		Long curso, String nome, String ementa, String preRequisitos, Role.Area area, String observacao, int chTotal,
		int chTeorica, int chPratica, String bibliografia, List<Nota> notas) {
}
