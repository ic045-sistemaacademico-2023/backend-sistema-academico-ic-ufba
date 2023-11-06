package com.ic045.sistemaacademico.services;

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

}
