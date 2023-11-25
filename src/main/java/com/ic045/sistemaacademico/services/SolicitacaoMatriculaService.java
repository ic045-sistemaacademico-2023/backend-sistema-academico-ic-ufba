package com.ic045.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.SolicitacaoMatriculaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

import java.util.List;

@Service
public class SolicitacaoMatriculaService {

    @Autowired
    private SolicitacaoMatriculaRepository repository;

    public List<SolicitacaoMatricula> getAllSolicitacoesMatricula() {
        return repository.findAll();
    }

    public SolicitacaoMatricula getSolicitacaoMatriculaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Matrícula", id)));
    }

    public SolicitacaoMatricula saveSolicitacaoMatricula(SolicitacaoMatricula solicitacaoMatricula) {
        return repository.save(solicitacaoMatricula);
    }

    public void deleteSolicitacaoMatricula(Long id) {
        repository.deleteById(id);
    }
}
