package com.ic045.sistemaacademico.controller.vos.request;

import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Role;

public record UpdateDisciplinaRequest(
		Long curso, String nome, String ementa, String preRequisitos, Role.Area area, String observacao, int chTotal,
		int chTeorica, int chPratica, String bibliografia, Nota nota, Integer faltas) {
}
