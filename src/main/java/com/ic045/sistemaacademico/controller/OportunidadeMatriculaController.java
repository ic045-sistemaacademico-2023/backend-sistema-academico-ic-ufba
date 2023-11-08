package com.ic045.sistemaacademico.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ic045.sistemaacademico.controller.vos.request.DisciplinaTurmas;
import com.ic045.sistemaacademico.controller.vos.request.InsertOportunidadeMatriculaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateOportunidadeMatriculaRequest;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.services.DisciplinaService;
import com.ic045.sistemaacademico.services.OpMatriculaDisciplinaTurmaService;
import com.ic045.sistemaacademico.services.OportunidadeMatriculaService;
import com.ic045.sistemaacademico.services.TurmaService;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@RestController
@RequestMapping("/oportunidade")
public class OportunidadeMatriculaController {
	
	@Autowired
	private OportunidadeMatriculaService service;	
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private OpMatriculaDisciplinaTurmaService opMatriculaDisciplinaTurmaService;
	
	@PostMapping("/")
	public ResponseEntity<OportunidadeMatricula> InsertOportunidade(@RequestBody InsertOportunidadeMatriculaRequest request) {
		Map<Disciplina, List<Turma>> disciplinasTurmas = new HashMap<Disciplina, List<Turma>>();
		
		for (DisciplinaTurmas dts : request.disciplinaTurmas()) {
			try {
				Disciplina disc = disciplinaService.findById(dts.disciplina());
				ArrayList<Turma> turmasDaDisciplina = new ArrayList<Turma>();
				
				for (Long turmaId : dts.turmas()) {
					try {
						Turma turma = turmaService.findById(turmaId);
						turmasDaDisciplina.add(turma);
					} catch (NotFoundException e) {
						throw new NotCreatedException(
								String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Turma", turmaId));
					}
				}
				disciplinasTurmas.put(disc, turmasDaDisciplina);
				
			} catch (NotFoundException e) {
				throw new NotCreatedException(
						String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Disciplina", dts.disciplina()));
			}
		}
		
		Timestamp dataInicial = Timestamp.valueOf(request.dataInicial());
		Timestamp dataFinal = Timestamp.valueOf(request.dataFinal());
		OportunidadeMatricula opMatricula = new OportunidadeMatricula(request.nome(), request.descricao(), dataInicial,
				dataFinal, request.aberta());
		
		opMatricula = service.insertOportunidadeMatriculaData(opMatricula);
		
		if(!disciplinasTurmas.isEmpty()) {
			for(Map.Entry<Disciplina, List<Turma>> entry : disciplinasTurmas.entrySet()) {
				for(Turma turma : entry.getValue()) {
					OpMatriculaDisciplinaTurma opMatDiscTurma = new OpMatriculaDisciplinaTurma(opMatricula,entry.getKey(),turma);
					opMatriculaDisciplinaTurmaService.insertOpMatriculaDisciplinaTurmaData(opMatDiscTurma);
				}
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(opMatricula);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OportunidadeMatricula> findById(@PathVariable Long id){
		OportunidadeMatricula opMatricula = service.findById(id);
		return opMatricula != null ? ResponseEntity.ok(opMatricula) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<OportunidadeMatricula> editOportunidadeMatricula(@PathVariable Long id,
			@RequestBody UpdateOportunidadeMatriculaRequest request) {
		OportunidadeMatricula opMat = service.updateOportuidadeMatricula(id,request);
				return ResponseEntity.status(HttpStatus.OK).body(opMat);
	}
	
	@PutMapping("/adddisciplinaturma/{id}")
	public ResponseEntity<Boolean> addDisciplinaTurma(@PathVariable Long id,
			@RequestBody DisciplinaTurmas request) {
		OportunidadeMatricula opMatricula = service.findById(id);
		Disciplina disc = disciplinaService.findById(request.disciplina());
		for(Long turmaId : request.turmas()) {
			Turma turma = turmaService.findById(turmaId);
			OpMatriculaDisciplinaTurma opMatDiscTurma = new OpMatriculaDisciplinaTurma(opMatricula,disc,turma);
			opMatriculaDisciplinaTurmaService.insertOpMatriculaDisciplinaTurmaData(opMatDiscTurma);
		}
		return ResponseEntity.ok(true);
	}
	
	@PutMapping("/removedisciplina/{idOpMatDisTurma}/{idDisciplina}")
	public ResponseEntity<Void> removeDisciplina(@PathVariable Long idOpMatDisTurma, @PathVariable Long idDisciplina){
		OpMatriculaDisciplinaTurma opMatDisTur = opMatriculaDisciplinaTurmaService.findByIdAndDisciplinaId(idOpMatDisTurma,idDisciplina);
		opMatriculaDisciplinaTurmaService.deleteById(opMatDisTur.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/removeturma/{idOpMatDisTurma}/{idTurma}")
	public ResponseEntity<Void> removeTurma(@PathVariable Long idOpMatDisTurma, @PathVariable Long idTurma){
		OpMatriculaDisciplinaTurma opMatDisTur = opMatriculaDisciplinaTurmaService.findByIdAndTurmaId(idOpMatDisTurma,idTurma);
		opMatriculaDisciplinaTurmaService.deleteById(opMatDisTur.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOportunidadeMatricula(@PathVariable Long id){
		List<OpMatriculaDisciplinaTurma> opMats = opMatriculaDisciplinaTurmaService.findByOportunidadeMatriculaId(id);
		
		for(OpMatriculaDisciplinaTurma omdt : opMats) 
			opMatriculaDisciplinaTurmaService.deleteById(omdt.getId());
		
		service.deleteOportunidadeMatricula(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
