package com.ic045.sistemaacademico.services;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.UpdateUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Professor;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.repositories.NotaRepository;

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.controller.vos.request.InsertTurmaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateNotaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateTurmaRequest;

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

        //Aluno aluno = new Aluno();
        //Turma turma = new Turma();
        //Nota new nota;

        Nota nota = new Nota();

        nota.setNota(insertNotaRequest.nota());


        //aluno.setId(insertNotaRequest.id_aluno());
        //turma.setId(insertNotaRequest.id_turma());

        //nota = Nota(insertNotaRequest.id_aluno(), insertNotaRequest.id_turma());


        return repository.save(nota);


    }


	public void deleteNota(Long id) {
        Nota nota = findById(id);

        repository.delete(nota);
    }


	public Nota updateNota(Long id, UpdateNotaRequest request) {
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
