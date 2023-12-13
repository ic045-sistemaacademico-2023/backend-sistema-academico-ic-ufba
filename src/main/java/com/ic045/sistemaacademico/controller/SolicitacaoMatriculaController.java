package com.ic045.sistemaacademico.controller;

import java.util.List;

import com.ic045.sistemaacademico.controller.vos.request.InsertSolicitacaoMatriculaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateSolicitacaoMatriculaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic045.sistemaacademico.controller.vos.request.InsertSolicitacaoMatriculaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateSolicitacaoMatriculaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;
import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.services.AlunoService;
import com.ic045.sistemaacademico.services.OportunidadeMatriculaService;
import com.ic045.sistemaacademico.services.SolicitacaoMatriculaService;
import com.ic045.sistemaacademico.services.SolicitacaoTurmaService;
import com.ic045.sistemaacademico.services.TurmaService;

@RestController
@RequestMapping("/solicitacao-matricula")
public class SolicitacaoMatriculaController {

    @Autowired
    private SolicitacaoMatriculaService service;
    
    @Autowired
    private SolicitacaoTurmaService solicitacaoTurmaService;

    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private OportunidadeMatriculaService oportunidadeMatriculaService;
    
    @Autowired
    private TurmaService turmaService;
    
    @GetMapping
    public List<SolicitacaoMatricula> getAllSolicitacoesMatricula() {
        return service.getAllSolicitacoesMatricula();
    }

    @GetMapping("/{id}")
    public SolicitacaoMatricula getSolicitacaoMatriculaById(@PathVariable Long id) {
        return service.getSolicitacaoMatriculaById(id);
    }

    @GetMapping("/coordenador/{id}")
    public List<SolicitacaoMatricula> getSolicitacaoMatriculaByCoordenadorId(@PathVariable Long id) {
        return service.getSolicitacaoMatriculaByCoordenadorId(id);
    }

    @GetMapping("/aluno/{id}")
    public SolicitacaoMatricula getSolicitacaoMatriculaByAlunoId(@PathVariable Long id) {
        return service.getSolicitacaoMatriculaByAlunoId(id);
    }

    @PostMapping
    public SolicitacaoMatricula saveSolicitacaoMatricula(@RequestBody InsertSolicitacaoMatriculaRequest request) {
        return service.saveSolicitacaoMatricula(request);
    }

    @PutMapping("/{id}")
    public SolicitacaoMatricula updateSolicitacaoMatricula(@PathVariable Long id,
            @RequestBody UpdateSolicitacaoMatriculaRequest request) {
        return service.updateSolicitacaoMatricula(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitacaoMatricula(@PathVariable Long id) {
        service.deleteSolicitacaoMatricula(id);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> verificarStatusMatricula(@PathVariable Long id) {
        String statusMatricula = service.verificarStatusMatricula(id);
        return new ResponseEntity<>(statusMatricula, HttpStatus.OK);
    }
    
	@PostMapping("/matricular/{alunoId}/{oportunidadeId}/{turmaId}")
	public ResponseEntity<SolicitacaoMatricula> matricularAluno(@PathVariable Long alunoId, @PathVariable Long oportunidadeId,
			@PathVariable Long turmaId) {
		OportunidadeMatricula oportunidadeMatricula =  oportunidadeMatriculaService.findById(oportunidadeId);
		
		if(!oportunidadeMatricula.getAberta()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		SolicitacaoMatricula solicitacaoMatricula = service.getSolicitacaoMatriculaByAlunoIdAndOportunidadeId(alunoId, oportunidadeId);
		Aluno aluno = alunoService.findById(alunoId);
		Turma turma = turmaService.findById(turmaId);
		InsertSolicitacaoMatriculaRequest insertRequest = new InsertSolicitacaoMatriculaRequest(alunoId, new Long[]{turmaId});
		
		if(solicitacaoMatricula == null) {//Cria solicitação de matrícula e de turma e a aprova 
			solicitacaoMatricula = service.saveSolicitacaoMatricula(insertRequest);
			solicitacaoTurmaService.aprovarSolicitacaoTurma(solicitacaoMatricula.getSolicitacoesTurma()
					.get(solicitacaoMatricula.getSolicitacoesTurma().size() - 1));
		}else {
			try { //se já existe uma solicitação do aluno para turma pendente, aprova imediatamente
				List<SolicitacaoTurma> solicitacoesTurma = solicitacaoTurmaService.findBySolicitacaoMatriculaId(solicitacaoMatricula.getId());
				for(SolicitacaoTurma solTurma : solicitacoesTurma) {
					if(solTurma.getTurma().getId() == turmaId) {
						solicitacaoTurmaService.aprovarSolicitacaoTurma(solTurma);
						solicitacaoMatricula = service.getSolicitacaoMatriculaById(solicitacaoMatricula.getId());
						return ResponseEntity.status(HttpStatus.OK).body(solicitacaoMatricula);
					}
				}
				//se não existe, insere nova solicitação de turma na solicitação de matricula e a parova
				SolicitacaoTurma novaSolicitacaoTurma = new SolicitacaoTurma(solicitacaoMatricula,turma,aluno);
				novaSolicitacaoTurma = solicitacaoTurmaService.saveSolicitacaoTurma(novaSolicitacaoTurma);
				solicitacoesTurma.add(novaSolicitacaoTurma);
				solicitacaoMatricula.setSolicitacoesTurma(solicitacoesTurma);
				solicitacaoTurmaService.aprovarSolicitacaoTurma(solicitacaoMatricula.getSolicitacoesTurma()
						.get(solicitacaoMatricula.getSolicitacoesTurma().size() - 1));
			}catch(NotFoundException e) {
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoMatricula);
	}
}