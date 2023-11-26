package com.ic045.sistemaacademico.services;

import java.util.List;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.domain.models.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.SolicitacaoTurmaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class SolicitacaoTurmaService {

    @Autowired
    private SolicitacaoTurmaRepository repository;

    @Autowired
    private TurmaService turmaService;

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

    public void deleteAllTurmas(Long id) {
        repository.deleteAllBySolicitacaoMatriculaId(id);
    }

    public void deleteSolicitacaoTurma(Long id) {
        repository.deleteById(id);
    }

    public void aprovarSolicitacaoTurma(SolicitacaoTurma solicitacaoTurma) {
        solicitacaoTurma.setStatus(Role.Status.APPROVED);
        turmaService.adicionarAlunoTurma(solicitacaoTurma.getTurma().getId(), solicitacaoTurma.getSolicitacaoMatricula().getAluno().getId());
        repository.save(solicitacaoTurma);
    }

    public void recusarSolicitacaoTurma(SolicitacaoTurma solicitacaoTurma) {
        solicitacaoTurma.setStatus(Role.Status.DENIED);
        repository.save(solicitacaoTurma);
    }

    public List<SolicitacaoTurma> findBySolicitacaoMatriculaId(Long solicitacaoMatriculaId){
    	List<SolicitacaoTurma> solicitacoes = repository.findAllBySolicitacaoMatriculaId(solicitacaoMatriculaId);
    	if(solicitacoes.isEmpty()) {
    		throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", solicitacaoMatriculaId));
    	}
    	return solicitacoes;
    }

    public List<SolicitacaoTurma> getSolicitacaoTurmasByTurmaId(Long idTurma) {
        return repository.findByTurmaId(idTurma);
    }

    public List<Turma> getTurmasByAlunoId(Long alunoId){
    	List<SolicitacaoTurma> solicitacoes = repository.findAllByAlunoId(alunoId);
    	if(solicitacoes.isEmpty()) {
    		throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", alunoId));
    	}
    	return solicitacoes.stream().map(SolicitacaoTurma::getTurma).collect(Collectors.toList());
    }
}