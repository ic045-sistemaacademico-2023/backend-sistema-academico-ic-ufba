package com.ic045.sistemaacademico.domain.projections;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;

public interface TurmaProjection {

    Long getId();
    String getDias();
    String getHorario();
    String getLocal();
    String getSemestre();
    Disciplina getDisciplina();
    Professor getProfessor();
}