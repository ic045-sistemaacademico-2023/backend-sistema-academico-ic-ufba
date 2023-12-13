package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.controller.vos.request.UpdateNotaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.NotaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

import java.util.List;

@Service
public class NotaService {
    @Autowired
    private NotaRepository repository;

    @Autowired
    public AlunoService alunoService;

    @Autowired
    public TurmaService turmaService;

    public Nota findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Nota", id)));
    }

    public List<Nota> findByAlunoId(Long id){
    	return repository.findByAlunoId(id);
    }

    public Nota insertNotaEFaltas(InsertNotaRequest insertNotaRequest) {

        Aluno aluno = new Aluno();
        Turma turma = new Turma();
        Nota nota = new Nota();
        Disciplina disciplina = new Disciplina();

        nota.setNota(insertNotaRequest.nota());
        nota.setFaltas(insertNotaRequest.faltas());
        aluno.setId(insertNotaRequest.aluno());
        turma.setId(insertNotaRequest.turma());
        disciplina.setId(insertNotaRequest.disciplina());
        nota.setAluno(aluno);
        nota.setTurma(turma);
        nota.setDisciplina(disciplina);

        try {
        	return repository.save(nota);
        }catch(Exception ex) {
        	if(insertNotaRequest.nota() == null)
        		throw new BadRequestException();
        	if (insertNotaRequest.aluno() == null)
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Aluno", "null"));
        	if (insertNotaRequest.turma() == null)
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", "null"));
            throw new NotCreatedException();
        }
    }


	public void deleteNota(Long id) {
        Nota nota = findById(id);

        repository.delete(nota);
    }


	public Nota updateNota(Long id, InsertNotaRequest request) {
        Nota notaToUpdate = findById(id);
        Aluno aluno = alunoService.findById(request.aluno());
        Turma turma = turmaService.findById(request.turma());

        notaToUpdate.setAluno(aluno);
        notaToUpdate.setTurma(turma);
        notaToUpdate.setNota(request.nota());

        try {
            return repository.save(notaToUpdate);
        } catch (Exception ex) {
            throw new BadRequestException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Nota", id));
        }

	}

    public Nota findByAlunoAndTurma(Aluno aluno, Turma turma) {
        return repository.findByAlunoIdAndTurmaId(aluno.getId(), turma.getId());
    }

    public Nota updateNotasEFaltas(Long id, UpdateNotaRequest request) {
        Nota notaToUpdate = findById(id);
        Aluno aluno = alunoService.findById(request.aluno());
        Turma turma = turmaService.findById(request.turma());

        notaToUpdate.setAluno(aluno);
        notaToUpdate.setTurma(turma);
        notaToUpdate.setNota(request.nota());
        notaToUpdate.setFaltas(request.faltas());

        try {
            return repository.save(notaToUpdate);
        } catch (Exception ex) {
            throw new BadRequestException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Nota", id));
        }

    }



}
