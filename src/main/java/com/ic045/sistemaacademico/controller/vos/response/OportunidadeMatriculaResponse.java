package com.ic045.sistemaacademico.controller.vos.response;

import java.util.List;

import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;

public record OportunidadeMatriculaResponse(OportunidadeMatricula oportunidadeMatricula, List<DisciplinaTurmasResponse> disciplinaTurmas) {

}
