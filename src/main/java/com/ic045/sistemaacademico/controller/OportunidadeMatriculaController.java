package com.ic045.sistemaacademico.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.ic045.sistemaacademico.controller.vos.request.OportunidadeMatriculaRequest;
import com.ic045.sistemaacademico.controller.vos.response.DisciplinaTurmasResponse;
import com.ic045.sistemaacademico.controller.vos.response.OportunidadeMatriculaResponse;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.services.CoordenadorDeCursoService;
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
	private CoordenadorDeCursoService coordenadorService;
	
	@Autowired
	private OpMatriculaDisciplinaTurmaService opMatriculaDisciplinaTurmaService;
	
	
	/*
	 * Recebe uma lista de objetos OportunidadeMatricula e retorna uma lista destes objetos formatados para resposta
	 * incluindo suas turmas e disciplinas asssociadas
	 * --> [ {oportunidadeMatricula: OportunidadeMatricula, disciplinaTurmas: [DisciplinaTurmasResponse] }, ... ]
	 */
	private List<OportunidadeMatriculaResponse> buildOportunidadeMatriculaResponse(
			List<OportunidadeMatricula> oportunidadesMatriculas) {
		List<OportunidadeMatriculaResponse> response = new ArrayList<OportunidadeMatriculaResponse>();
		// Para cada oportunidade de matricula, busca as disciplinas e turmas associadas
		for (OportunidadeMatricula opMat : oportunidadesMatriculas) {
			List<OpMatriculaDisciplinaTurma> opMatDiscTurmaList = opMatriculaDisciplinaTurmaService
					.findByOportunidadeMatriculaId(opMat.getId());
			Map<Disciplina, List<Turma>> disciplinasTurmas = new HashMap<Disciplina, List<Turma>>();
			
			if (!opMatDiscTurmaList.isEmpty()) {
				for (OpMatriculaDisciplinaTurma opMatDiscTurma : opMatDiscTurmaList) {
					if (disciplinasTurmas.containsKey(opMatDiscTurma.getDisciplina()))
						disciplinasTurmas.get(opMatDiscTurma.getDisciplina()).add(opMatDiscTurma.getTurma());
					else
						disciplinasTurmas.put(opMatDiscTurma.getDisciplina(),
								new ArrayList<Turma>(Arrays.asList(opMatDiscTurma.getTurma())));
				}

				// {disciplina: Disciplina, turmas: Turmas[]}
				List<DisciplinaTurmasResponse> disciplinaTurmasReponse = new ArrayList<DisciplinaTurmasResponse>();
				for (Disciplina disc : disciplinasTurmas.keySet()) {
					disciplinaTurmasReponse
							.add(new DisciplinaTurmasResponse(disc, disciplinasTurmas.get(disc)));
				}

				// [ {oportunidadeMatricula: OportunidadeMatricula, disciplinaTurmas: [DisciplinaTurmasResponse] } ]
				response.add(new OportunidadeMatriculaResponse(opMat, disciplinaTurmasReponse));
			}else {
				response.add(new OportunidadeMatriculaResponse(opMat, new ArrayList<DisciplinaTurmasResponse>()));
			}
		}
		return response;
	}
	
	/*
	 * Constroi um hash map das disciplinas com suas turnas a partir da requisição
	 */
	private Map<Disciplina, List<Turma>> mapDisciplinasTurmas(OportunidadeMatriculaRequest request){
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
		return disciplinasTurmas;
	}
	
	/*
	 * Valida se existe alguma oportunidade de matrícula para o curso informado que já esteja aberta
	 * Caso afirmativo, lança exceção que é mapeada para status 501 na resposta
	 */
	private void validateThereIsOportunidadeAlreadyOpen(Long coordenadorId) {
		if(service.isThereAlreadyOpen(coordenadorId))
			throw new NotCreatedException(
					String.format("There is already an open enrollment opportunity for this course"));
	}
	
	@PostMapping("/")
	public ResponseEntity<OportunidadeMatricula> InsertOportunidade(@RequestBody OportunidadeMatriculaRequest request) {
		if(request.aberta())
			validateThereIsOportunidadeAlreadyOpen(request.coordenador());
		
		Map<Disciplina, List<Turma>> disciplinasTurmas = mapDisciplinasTurmas(request);
		
		Timestamp dataInicial = Timestamp.valueOf(request.dataInicial());
		Timestamp dataFinal = Timestamp.valueOf(request.dataFinal());
		CoordenadorDeCurso coordenador = coordenadorService.findById(request.coordenador());
		OportunidadeMatricula opMatricula = new OportunidadeMatricula(request.nome(), request.descricao(), dataInicial,
				dataFinal, request.aberta(), coordenador);
		
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
	public ResponseEntity<OportunidadeMatriculaResponse> findById(@PathVariable Long id){
		OportunidadeMatricula opMatricula = service.findById(id);
		List<OportunidadeMatriculaResponse> response = buildOportunidadeMatriculaResponse(Arrays.asList(opMatricula));
		return response.size() != 0 ? ResponseEntity.ok(response.get(0)) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OportunidadeMatriculaResponse>> findAll(){
		List<OportunidadeMatricula> oportunidadesMatriculas = service.findAll();
		List<OportunidadeMatriculaResponse> response = buildOportunidadeMatriculaResponse(oportunidadesMatriculas);
		return  ResponseEntity.ok(response);
	}
	
	@GetMapping("/bycoordenadorid/{id}")
	public ResponseEntity<List<OportunidadeMatriculaResponse>> finByCoordenadorId(@PathVariable Long id){
		List<OportunidadeMatricula> oportunidadesMatriculas = service.findByCoordenadorId(id);
		List<OportunidadeMatriculaResponse> response = buildOportunidadeMatriculaResponse(oportunidadesMatriculas);
		return  ResponseEntity.ok(response);
	}

	@GetMapping("/bycursoid/{id}")
	public ResponseEntity<List<OportunidadeMatriculaResponse>> finByCursoId(@PathVariable Long id){
		List<OportunidadeMatricula> oportunidadesMatriculas = service.findByCursoId(id);
		List<OportunidadeMatriculaResponse> response = buildOportunidadeMatriculaResponse(oportunidadesMatriculas);
		return  ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OportunidadeMatricula> editOportunidadeMatricula(@PathVariable Long id,
			@RequestBody OportunidadeMatriculaRequest request) {
		if (request.aberta())
			validateThereIsOportunidadeAlreadyOpen(request.coordenador());

		OportunidadeMatricula opMat = service.findById(id);

		List<OpMatriculaDisciplinaTurma> opMatDiscTurmas = opMatriculaDisciplinaTurmaService
				.findByOportunidadeMatriculaId(id);
		for (OpMatriculaDisciplinaTurma opMatDiscTurma : opMatDiscTurmas)
			opMatriculaDisciplinaTurmaService.deleteById(opMatDiscTurma.getId());

		Map<Disciplina, List<Turma>> disciplinasTurmas = mapDisciplinasTurmas(request);
		if (!disciplinasTurmas.isEmpty()) {
			for (Map.Entry<Disciplina, List<Turma>> entry : disciplinasTurmas.entrySet()) {
				for (Turma turma : entry.getValue()) {
					OpMatriculaDisciplinaTurma opMatDiscTurma = new OpMatriculaDisciplinaTurma(opMat, entry.getKey(),
							turma);
					opMatriculaDisciplinaTurmaService.insertOpMatriculaDisciplinaTurmaData(opMatDiscTurma);
				}
			}
		}

		opMat = service.updateOportuidadeMatricula(id, request);
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
	
	@PutMapping("/removeturma/{idOpMat}/{idDisciplina}/{idTurma}")
	public ResponseEntity<Void> removeTurma(@PathVariable Long idOpMat, @PathVariable Long idDisciplina,
			@PathVariable Long idTurma) {
		OpMatriculaDisciplinaTurma opMatDisTur = opMatriculaDisciplinaTurmaService
				.findByOportunidadeMatriculaIdAndDisciplinaIdAndTurmaId(idOpMat, idDisciplina, idTurma);
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
