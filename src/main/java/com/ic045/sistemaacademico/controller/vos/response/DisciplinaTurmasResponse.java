package com.ic045.sistemaacademico.controller.vos.response;

import java.util.List;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Turma;

public record DisciplinaTurmasResponse (Disciplina disciplina, List<Turma> turmas){

}
