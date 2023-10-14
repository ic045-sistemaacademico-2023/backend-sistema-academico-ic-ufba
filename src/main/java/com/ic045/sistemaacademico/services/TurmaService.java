package com.ic045.sistemaacademico.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateTurmaRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    @Autowired
    public DisciplinaService disciplinaService;

    @Autowired
    public ProfessorService professorService;

    public Turma findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id)));
    }

    public List<Turma> findForSemestreData(String period) {
        return repository
                .findBysemestre(period).orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", "semester", period)));
    }

    public Turma insertTurmaData(InsertTurmaRequest insertTurmaRequest) {
        Disciplina disciplina = new Disciplina();
        Professor professor = new Professor();
        Turma turma;
        String data = Arrays
                .stream(insertTurmaRequest.dias())
                .map(Role.Date::getCodeDate)
                .collect(Collectors.joining(","));

        disciplina.setId(insertTurmaRequest.disciplina());

        if (insertTurmaRequest.professor() != null) {
            professor.setId(insertTurmaRequest.professor());
            turma = new Turma(disciplina, professor, data, insertTurmaRequest.horario(), insertTurmaRequest.local(), insertTurmaRequest.semestre());
        } else {
            turma = new Turma(disciplina, data, insertTurmaRequest.horario(), insertTurmaRequest.local(), insertTurmaRequest.semestre());
        }

        try {
            return repository.save(turma);
        } catch (Exception ex) {
            throw new NotCreatedException();
        }
    }

    public List<Turma> findTurmasByAlunoId(Long alunoId) {
        return repository.findAllByAlunosId(alunoId);
    }

    public void deleteTurma(Long id) {
        Turma turma = findById(id);

        repository.delete(turma);
    }

    public Turma updateTurma(Long id, UpdateTurmaRequest request) {
        Turma turmaToUpdate = findById(id);
        Disciplina disciplina = disciplinaService.findById(request.disciplina());
        Professor professor = professorService.findById(request.professor());

        turmaToUpdate.setDisciplina(disciplina);
        turmaToUpdate.setProfessor(professor);
        turmaToUpdate.setDias(request.dias());
        turmaToUpdate.setHorario(request.horario());
        turmaToUpdate.setLocal(request.local());
        turmaToUpdate.setSemestre(request.semestre());

        try {
            return repository.save(turmaToUpdate);
        } catch (Exception ex) {
            throw new BadRequestException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id));
        }
    }

}
