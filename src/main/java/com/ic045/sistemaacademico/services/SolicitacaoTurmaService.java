package com.ic045.sistemaacademico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.SolicitacaoTurmaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class SolicitacaoTurmaService {

    @Autowired
    private SolicitacaoTurmaRepository repository;

    public List<SolicitacaoTurma> getAllSolicitacoesTurma() {
        return repository.findAll();
    }

    public SolicitacaoTurma getSolicitacaoTurmaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", id)));
    }

    public SolicitacaoTurma saveSolicitacaoTurma(SolicitacaoTurma solicitacaoTurma) {
        return repository.save(solicitacaoTurma);
    }

    public void deleteSolicitacaoTurma(Long id) {
        repository.deleteById(id);
    }

    // Adicione outros métodos de serviço conforme necessário
}