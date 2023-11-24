package com.ic045.sistemaacademico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;
import com.ic045.sistemaacademico.repositories.OpMatriculaDisciplinaTurmaRepository;

@Service
public class OpMatriculaDisciplinaTurmaService {
	

	@Autowired
	private OpMatriculaDisciplinaTurmaRepository repository;
	
	
	public OpMatriculaDisciplinaTurma insertOpMatriculaDisciplinaTurmaData(OpMatriculaDisciplinaTurma opMatDiscTurma) {
		return repository.save(opMatDiscTurma);
	}
	
	public OpMatriculaDisciplinaTurma findByIdAndDisciplinaId(Long id, Long idDisciplina) {
		return repository.findByIdAndDisciplinaId(id,idDisciplina);
	}
	
	public OpMatriculaDisciplinaTurma findByOportunidadeMatriculaIdAndDisciplinaIdAndTurmaId(Long idOpMat, Long idDisciplina, Long idTurma) {
		return repository.findByOportunidadeMatriculaIdAndDisciplinaIdAndTurmaId(idOpMat,idDisciplina,idTurma);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public List<OpMatriculaDisciplinaTurma> findByOportunidadeMatriculaId(Long id) {
		return repository.findByOportunidadeMatriculaId(id);
	}

}
