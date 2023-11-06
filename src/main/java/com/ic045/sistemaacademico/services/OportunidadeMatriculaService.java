package com.ic045.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.exception.custom.NotCreatedException;
import com.ic045.sistemaacademico.repositories.OportunidadeMatriculaRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class OportunidadeMatriculaService {

	@Autowired
	private OportunidadeMatriculaRepository repository;
	
	
	public OportunidadeMatricula insertOportunidadeMatriculaData(OportunidadeMatricula opMatricula) {
		return repository.save(opMatricula);
	}
	
}
