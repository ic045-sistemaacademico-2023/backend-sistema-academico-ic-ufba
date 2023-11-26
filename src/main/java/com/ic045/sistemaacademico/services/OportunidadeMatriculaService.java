package com.ic045.sistemaacademico.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.OportunidadeMatriculaRequest;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.OportunidadeMatriculaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class OportunidadeMatriculaService {

	@Autowired
	private OportunidadeMatriculaRepository repository;
	
	@Autowired
	private CoordenadorDeCursoService coordenadorService;
	
	public OportunidadeMatricula insertOportunidadeMatriculaData(OportunidadeMatricula opMatricula) {
		return repository.save(opMatricula);
	}
	
	public OportunidadeMatricula findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(
				String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "OportunidadeMatricula", id)));
	}
	
	public OportunidadeMatricula updateOportuidadeMatricula(Long id, OportunidadeMatriculaRequest request) {
		OportunidadeMatricula opMat = findById(id);
		if(request.coordenador() != null) {
			CoordenadorDeCurso coordenador = coordenadorService.findById(request.coordenador());
			opMat.setCoordenador(coordenador);
		}
		opMat.setNome(request.nome());
		opMat.setDescricao(request.descricao());
		opMat.setDataInicial(Timestamp.valueOf(request.dataInicial()));
		opMat.setDataFinal(Timestamp.valueOf(request.dataFinal()));
		opMat.setAberta(request.aberta());
		return repository.save(opMat);
	}

	public void deleteOportunidadeMatricula(Long id) {
		OportunidadeMatricula opMat = findById(id);
		repository.delete(opMat);
	}

	public List<OportunidadeMatricula> findAll() {
		return repository.findAll();
	}

	public OportunidadeMatricula findByCoordenadorIdAndAberta(Long id) {
		return repository.findByCoordenadorIdAndAberta(id);
	}

	public List<OportunidadeMatricula> findByCoordenadorId(Long id) {
		return repository.findByCoordenadorId(id);
	}

	public List<OportunidadeMatricula> findByCursoId(Long id) {
		return repository.findByCursoId(id);
	}


	public boolean isThereAlreadyOpen(Long coordenadorId) {
		int count = repository.countAbertaByCoordenadorId(coordenadorId);
		return count > 0;
	}
	
	
}
