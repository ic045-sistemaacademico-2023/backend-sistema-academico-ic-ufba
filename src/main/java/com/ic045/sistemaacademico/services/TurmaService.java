package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateTurmaRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.TurmaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public List<Turma> findAll(){
    	List<Turma> turmas = repository.findAll();
        if (turmas.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turmas"));
        }
        return turmas;
    }
    
    public List<Turma> findByDisciplinaId(Long disciplinaId){
    	List<Turma> turmas = repository.findAllByDisciplinaId(disciplinaId);
    	if (turmas.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", disciplinaId));
        }
        return turmas;
    }
    

    public List<Turma> findForSemestreData(String nrsemestre) {
    	List<Turma> turmas = repository.findAllBySemestre(nrsemestre);
    	if(turmas.isEmpty()) {
    		throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", nrsemestre));
    	}
        return turmas;
    }

    public Turma insertTurmaData(InsertTurmaRequest insertTurmaRequest) {
        Disciplina disciplina = new Disciplina();
        Professor professor = new Professor();
        Turma turma;
        String data = Arrays
                .stream(insertTurmaRequest.dias())
                .map(Role.Date::getCodeDate)
                .collect(Collectors.joining(","));

        try {
        	disciplina = disciplinaService.findById(insertTurmaRequest.disciplina());
			professor.setId(insertTurmaRequest.professor());
			turma = new Turma(disciplina, professor, data, insertTurmaRequest.horario(), insertTurmaRequest.sala(),
					insertTurmaRequest.semestre());
			turma.setCode(generationCode(turma));
			return repository.save(turma);
        } catch (Exception ex) {
        	if (insertTurmaRequest.professor() == null)
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Professor", "null"));
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
    	try {
	    	Turma turmaToUpdate = findById(id);
	        Disciplina disciplina = disciplinaService.findById(request.disciplina());
	        Professor professor = professorService.findById(request.professor());
	
	        turmaToUpdate.setDisciplina(disciplina);
	        turmaToUpdate.setProfessor(professor);
	        turmaToUpdate.setDias(request.dias());
	        turmaToUpdate.setHorario(request.horario());
	        turmaToUpdate.setSala(request.sala());
	        turmaToUpdate.setSemestre(request.semestre());

            return repository.save(turmaToUpdate);
        } catch (Exception ex) {
        	if(request.disciplina() == null) 
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Disciplina", "null"));
			if(request.professor() == null)
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Professor", "null"));
            throw new BadRequestException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", id));
        }
    }

    public String generationCode(Turma turma) {
        String code = new StringBuilder().append(turma.getDisciplina().getArea()).append(repository.findFirstByOrderByIdDesc().get().getId() + 1).toString();
        return code;
    }

	public List<Turma> findTurmasDisponiveisMatricula() {
		return repository.findTurmasDisponiveisMatricula();
	}

    public List<Turma> findTurmasDisponiveisPorCursoId(Long cursoId) {
        return repository.findTurmasDisponiveisPorCursoId(cursoId);
    }
}
