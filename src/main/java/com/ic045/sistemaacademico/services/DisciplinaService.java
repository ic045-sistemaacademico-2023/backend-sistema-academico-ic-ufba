package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.DisciplinaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public Disciplina findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Disciplina", id)));
    }

    public List<Disciplina> findAllByCursoId(Long id) {
        return repository.findAllByCursoId(id);
    }

    public List<Turma> findAllByDisciplinaId(Long disciplinaId) {
        return repository.findAllTurmasByDisciplinaId(disciplinaId);
    }

    public boolean InsertDisciplinaData(Disciplina disciplina) {
        if (repository.exists(Example.of(disciplina))) throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
        disciplina = CodeDisciplina(disciplina);
       try {

            repository.save(disciplina);
            return true;
        }catch (IllegalArgumentException e){
            throw new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());
        }
        catch (OptimisticLockingFailureException e){
            throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
        }
    }

    public Disciplina CodeDisciplina(Disciplina disciplina){

        String code = new StringBuilder(Role.Area.valueOf(disciplina.getArea()).getCode())
                .insert(Role.Area.valueOf(disciplina.getArea()).getCode().length(),( repository.countByarea(disciplina.getArea()) + 1))
                .toString();
        disciplina.setCodigo(code);
        return disciplina;
    }

    public List<Disciplina> findAllDisciplinas() {
        return repository.findAll();
    }

    public boolean editDisciplina(Long id, Disciplina updatedDisciplina) {
        Disciplina existingDisciplina = findById(id);

        if (existingDisciplina != null) {
            existingDisciplina.setNome(updatedDisciplina.getNome());
            existingDisciplina.setEmenta(updatedDisciplina.getEmenta());
            existingDisciplina.setPreRequisitos(updatedDisciplina.getPreRequisitos());
            existingDisciplina.setArea(updatedDisciplina.getArea());
            existingDisciplina.setObservacao(updatedDisciplina.getObservacao());
            existingDisciplina.setChTotal(updatedDisciplina.getChTotal());
            existingDisciplina.setChTeorica(updatedDisciplina.getChTeorica());
            existingDisciplina.setChPratica(updatedDisciplina.getChPratica());
            existingDisciplina.setBibliografia(updatedDisciplina.getBibliografia());

            repository.save(existingDisciplina);

            return true;
        } else {
            return false;
        }
    }

    public void deleteDisciplina(Long id) {
        repository.deleteById(id);
    }
}
