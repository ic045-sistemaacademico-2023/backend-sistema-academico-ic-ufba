package com.ic045.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.NotaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

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

    
    public Nota insertNotaData(InsertNotaRequest insertNotaRequest) {

        Aluno aluno = new Aluno();
        Turma turma = new Turma();
        Nota nota = new Nota();

        nota.setNota(insertNotaRequest.nota());
        aluno.setId(insertNotaRequest.aluno());
        turma.setId(insertNotaRequest.turma());
        nota.setAluno(aluno);
        nota.setTurma(turma);
        
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



}
