package com.ic045.sistemaacademico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.repositories.SolicitacaoMatriculaRepository;
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

    @Autowired
    private SolicitacaoMatriculaRepository solicitacaoMatriculaRepository;

    public List<SolicitacaoTurma> getAllSolicitacoesTurma() {
        return repository.findAll();
    }

    public SolicitacaoTurma getSolicitacaoTurmaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", id)));
    }

    public List<SolicitacaoTurma> getSolicitacaoTurmaBySolicitacaoMatriculaId(Long id) {
        List<SolicitacaoTurma> solicitacoes = repository.findAllBySolicitacaoMatriculaId(id);

        if(solicitacoes.isEmpty()) {
        	throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", id));
        }
        return solicitacoes;
    }

    public SolicitacaoTurma getSolicitacaoTurmaByTurmaIdAndAlunoId(Long turmaId, Long alunoId) {
        SolicitacaoTurma solicitacaoTurma = repository.findByTurmaIdAndAlunoId(turmaId, alunoId);

        if(solicitacaoTurma == null) {
        	throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", turmaId, alunoId));
        }

        return solicitacaoTurma;
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

        SolicitacaoMatricula solicitacaoMatricula = solicitacaoTurma.getSolicitacaoMatricula();

        int qndSolicitacoesAprovadas = (int) solicitacaoMatricula.getSolicitacoesTurma().stream()
                .filter(solicitacao -> solicitacao.getStatus() == Role.Status.APPROVED)
                .count();

        int qndSolicitacoesRecusadas = (int) solicitacaoMatricula.getSolicitacoesTurma().stream()
                .filter(solicitacao -> solicitacao.getStatus() == Role.Status.DENIED)
                .count();

        int qndSolicitacaoTurmas = solicitacaoMatricula.getSolicitacoesTurma().size();

        if (qndSolicitacoesAprovadas == qndSolicitacaoTurmas) {
            solicitacaoMatricula.setStatus(Role.Status.APPROVED);
        } else if (qndSolicitacoesRecusadas == qndSolicitacaoTurmas) {
            solicitacaoMatricula.setStatus(Role.Status.DENIED);
        } else if (qndSolicitacoesRecusadas + qndSolicitacoesAprovadas == qndSolicitacaoTurmas) {
            solicitacaoMatricula.setStatus(Role.Status.FINISHED);
        }

        solicitacaoMatriculaRepository.save(solicitacaoMatricula);

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

    public List<SolicitacaoTurma> getTurmasByAlunoId(Long alunoId){
    	List<SolicitacaoTurma> solicitacoes = repository.findAllByAlunoId(alunoId);
    	if(solicitacoes.isEmpty()) {
    		throw new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Turma", alunoId));
    	}
    	return new ArrayList<>(solicitacoes);
    }
}