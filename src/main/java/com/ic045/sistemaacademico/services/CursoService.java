package com.ic045.sistemaacademico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ic045.sistemaacademico.controller.vos.request.UpdateCursoRequest;
import com.ic045.sistemaacademico.domain.models.CoordenadorDeCurso;
import com.ic045.sistemaacademico.domain.models.Curso;
import com.ic045.sistemaacademico.exception.custom.BadRequestException;
import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.CursoRepository;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;

@Service
public class CursoService {
	@Autowired
	private CursoRepository repository;

	@Autowired
	public CoordenadorDeCursoService coordenadorService;

	public Boolean InsertCursoData(Curso curso) {
		if (repository.exists(Example.of(curso))) {
			return false;
		}
		try {
			repository.save(curso);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	public Curso findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Curso", id)));
	}

	public void deleteCurso(Long id) {
		Curso curso = findById(id);
		repository.delete(curso);
	}

	public Curso updateCurso(Long id, UpdateCursoRequest request) {

		Curso cursoToUpdate = findById(id);
		CoordenadorDeCurso coordenador = coordenadorService.findById(request.coordenador());

		cursoToUpdate.setNome(request.nome());
		cursoToUpdate.setSemestre(request.semestre());
		cursoToUpdate.setTurno(request.turno());
		cursoToUpdate.setCoordenadorDeCurso(coordenador);

		try {
			return repository.save(cursoToUpdate);
		} catch (Exception ex) {
			throw new BadRequestException(String.format(ErrorMessages.OBJECT_NOT_FOUND.getMessage(), "Curso", id));
		}
	}

}
