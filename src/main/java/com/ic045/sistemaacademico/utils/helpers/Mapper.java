package com.ic045.sistemaacademico.utils.helpers;

import com.ic045.sistemaacademico.domain.dtos.DisciplinaDTO;
import com.ic045.sistemaacademico.domain.dtos.ProfessorDTO;
import com.ic045.sistemaacademico.domain.dtos.TurmaDTO;
import com.ic045.sistemaacademico.domain.models.Turma;

public class Mapper {
    public static TurmaDTO toDTO(Turma turma) {
        ProfessorDTO professorDTO = ProfessorMapper.toDTO(turma.getProfessor());
        DisciplinaDTO disciplinaDTO = DisciplinaMapper.toDTO(turma.getDisciplina());

        return new TurmaDTO(
                turma.getId(),
                turma.getLocal(),
                professorDTO,
                turma.getHorario(),
                disciplinaDTO,
                turma.getDias(),
                turma.getSemestre()
        );
    }
}
