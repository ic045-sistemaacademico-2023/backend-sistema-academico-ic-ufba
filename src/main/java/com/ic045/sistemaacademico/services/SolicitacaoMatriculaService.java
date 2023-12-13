package com.ic045.sistemaacademico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.InsertSolicitacaoMatriculaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateSolicitacaoMatriculaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.domain.models.Role;
import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;
import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.SolicitacaoMatriculaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

import jakarta.transaction.Transactional;

@Service
public class SolicitacaoMatriculaService {

    @Autowired
    private SolicitacaoMatriculaRepository repository;

    @Autowired
    private SolicitacaoTurmaService solicitacaoTurmaService;

    @Autowired
    private OportunidadeMatriculaService oportunidadeMatriculaService;

    @Autowired
    private AlunoService alunoService;

    public List<SolicitacaoMatricula> getAllSolicitacoesMatricula() {
        return repository.findAll();
    }

    public SolicitacaoMatricula getSolicitacaoMatriculaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Solicitação Matrícula", id)));
    }

    public List<SolicitacaoMatricula> getSolicitacaoMatriculaByCoordenadorId(Long id) {
        return repository.findAll().stream()
                .filter(solicitacaoMatricula -> Objects.equals(solicitacaoMatricula.getOportunidadeMatricula().getCoordenador().getId(), id))
                .toList();
    }

    public SolicitacaoMatricula getSolicitacaoMatriculaByAlunoId(Long id) {
        return repository.findByAlunoId(id);
    }
    
    public SolicitacaoMatricula getSolicitacaoMatriculaByAlunoIdAndOportunidadeId(Long alunoId, Long oportunidadeId) {
        return repository.findByAlunoIdAndOportunidadeMatriculaId(alunoId, oportunidadeId);
    }

    public SolicitacaoMatricula saveSolicitacaoMatricula(InsertSolicitacaoMatriculaRequest request) {
        Aluno aluno = alunoService.findById(request.aluno());
        CoordenadorDeCurso coordenador = aluno.getCurso().getCoordenadorDeCurso();
        OportunidadeMatricula oportunidadeMatricula = oportunidadeMatriculaService.findByCoordenadorIdAndAberta(coordenador.getId());

        SolicitacaoMatricula solicitacaoMatricula = new SolicitacaoMatricula(aluno, oportunidadeMatricula);
        SolicitacaoMatricula response = repository.save(solicitacaoMatricula);

        List<SolicitacaoTurma> solicitacoesTurmas = new ArrayList<SolicitacaoTurma>();
        for (Long idTurma : request.turmas()) {
            Turma turma = new Turma();
            turma.setId(idTurma);

            SolicitacaoTurma solicitacaoTurma = new SolicitacaoTurma(solicitacaoMatricula, turma, aluno);
            solicitacaoTurma = solicitacaoTurmaService.saveSolicitacaoTurma(solicitacaoTurma);
            solicitacoesTurmas.add(solicitacaoTurma);
        }
        
        response.setSolicitacoesTurma(solicitacoesTurmas);
        return response;
    }

    @Transactional
    public SolicitacaoMatricula updateSolicitacaoMatricula(Long id, UpdateSolicitacaoMatriculaRequest request) {
        SolicitacaoMatricula solicitacaoMatricula = getSolicitacaoMatriculaById(id);

        solicitacaoTurmaService.deleteAllTurmas(id);

        for (Long turmaId : request.turmas()) {
            Turma turma = new Turma();
            turma.setId(turmaId);
            SolicitacaoTurma solicitacaoTurma = new SolicitacaoTurma(solicitacaoMatricula, turma, solicitacaoMatricula.getAluno());
            solicitacaoTurmaService.saveSolicitacaoTurma(solicitacaoTurma);
        }

        return solicitacaoMatricula;
    }

    public void deleteSolicitacaoMatricula(Long id) {
        repository.deleteById(id);
    }

    public String verificarStatusMatricula(Long id) {
        List<SolicitacaoTurma> turmas = solicitacaoTurmaService.findBySolicitacaoMatriculaId(id);

        for (SolicitacaoTurma turma : turmas) {
            if (turma.getStatus() == Role.Status.WAITING_APPROVAL) {
                return "AGUARDANDO_APROVACAO_COORDENADOR";
            }
        }

        return "FINALIZADA";
    }

	public SolicitacaoMatricula saveSolicitacaoMatricula(SolicitacaoMatricula solicitacaoMatricula) {
		return repository.save(solicitacaoMatricula);
	}
}
