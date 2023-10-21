package com.ic045.sistemaacademico.services;

import java.util.List;

import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public Disciplina findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Disciplina", id)));
    }

    public Disciplina findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Disciplina> findAllByCursoId(Long id) {
        return repository.findAllByCursoId(id);
    }

    public Disciplina update(Long id, Disciplina disciplina) {
        Disciplina disciplinaAtualizada = findById(id);

        if (disciplinaAtualizada == null) throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Disciplina", id));

        try {
            disciplinaAtualizada.setNome(disciplina.getNome());
            disciplinaAtualizada.setEmenta(disciplina.getEmenta());
            disciplinaAtualizada.setArea(disciplina.getArea());
            disciplinaAtualizada.setChPratica(disciplina.getChPratica());
            disciplinaAtualizada.setChTeorica(disciplina.getChTeorica());
            disciplinaAtualizada.setChTotal(disciplina.getChTotal());
            disciplinaAtualizada.setSemestre(disciplina.getSemestre());

            if (disciplina.getObservacao() != null) disciplinaAtualizada.setObservacao(disciplina.getObservacao());
            if (disciplina.getObjetivos() != null) disciplinaAtualizada.setObjetivos(disciplina.getObjetivos());
            if (disciplina.getBibliografia() != null) disciplinaAtualizada.setBibliografia(disciplina.getBibliografia());
            if (disciplina.getConteudo() != null) disciplinaAtualizada.setConteudo(disciplina.getConteudo());
            if (disciplina.getPreRequisitos() != null) disciplinaAtualizada.setPreRequisitos(disciplina.getPreRequisitos());

            return repository.save(disciplinaAtualizada);
        } catch (IllegalArgumentException e) {
            throw new NotCreatedException(ErrorMessages.DATA_NULL.getMessage());
        } catch (OptimisticLockingFailureException e) {
            throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
        }
    }

    public boolean InsertDisciplinaData(Disciplina disciplina) {
        if (repository.exists(Example.of(disciplina))) throw new NotCreatedException(ErrorMessages.NOT_CREATED.getMessage());
        disciplina = CodeDisciplina(disciplina);
       try {
           System.out.println(disciplina.toString());
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
}
