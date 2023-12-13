package com.ic045.sistemaacademico.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.ic045.sistemaacademico.controller.vos.request.InsertNotaRequest;
import com.ic045.sistemaacademico.controller.vos.request.UpdateNotaRequest;
import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Nota;
import com.ic045.sistemaacademico.domain.models.Turma;
import com.ic045.sistemaacademico.services.AlunoService;
import com.ic045.sistemaacademico.services.EmailService;
import com.ic045.sistemaacademico.services.NotaService;
import com.ic045.sistemaacademico.services.TurmaService;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService service;
    
    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private TurmaService turmaService;
    
    @Autowired
    private EmailService emailService;
    

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota nota = service.findById(id);

        return nota != null ? ResponseEntity.ok(nota) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/aluno")
    public ResponseEntity<Aluno> findAlunoByNota(@PathVariable Long id) {
        Nota nota = service.findById(id);
        Aluno aluno = nota.getAluno();
        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }

    @GetMapping("/aluno/{id}")
    public List<Nota> findByAlunoId(@PathVariable Long id) {
        return service.findByAlunoId(id);
    }

    @PostMapping("/")
    public ResponseEntity<Nota> insertNota(@RequestBody InsertNotaRequest insertNotaRequest) {
        if (insertNotaRequest.nota() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertNotaEFaltas(insertNotaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nota> deleteNota(@PathVariable Long id) {
        service.deleteNota(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @RequestBody InsertNotaRequest request) {
        Nota nota = service.updateNota(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }

    @PutMapping("/{id}/notas-e-faltas")
    public ResponseEntity<Nota> updateNotasEFaltas(@PathVariable Long id, @RequestBody UpdateNotaRequest request) {
        Nota nota = service.updateNotasEFaltas(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }
    
	/*
	 * Endpoints para envio de notas
	 */

	@PostMapping("/enviar/aluno/{alunoId}/{turmaId}")
	public ResponseEntity<Void> enviarNotaAluno(@PathVariable Long alunoId, @PathVariable Long turmaId) {
		Aluno aluno = alunoService.findById(alunoId);
		Turma turma = turmaService.findById(turmaId);
		Nota nota = service.findByAlunoAndTurma(aluno, turma);

		if (nota == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		String emailSubject = String.format("Tomorrow - Nota em %s", turma.getDisciplina().getNome());
		String emailBody = String.format("%s, sua nota em %s foi de %.2f com %.1f faltas.",
				aluno.getUsuario().getNome(), turma.getDisciplina().getNome(), nota.getNota(), nota.getFaltas());

		if (emailService.sendEmail(aluno.getUsuario().getEmail(), emailSubject, emailBody))
			return ResponseEntity.status(HttpStatus.OK).build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping("/enviar/turma/{turmaId}")
	public ResponseEntity<List<String>> enviarNotasTurma(@PathVariable Long turmaId) {
		Turma turma = turmaService.findById(turmaId);
		Nota nota = null;
		List<String> notSentEmails = new ArrayList<String>();
		String emailSubject = String.format("Tomorrow - Nota em %s", turma.getDisciplina().getNome());
		String emailBody;

		for (Aluno aluno : turma.getAlunos()) {
			nota = service.findByAlunoAndTurma(aluno, turma);
			if (nota != null) {
				emailBody = String.format("%s, sua nota em %s foi de %.2f com %.1f faltas.",
						aluno.getUsuario().getNome(), turma.getDisciplina().getNome(), nota.getNota(),
						nota.getFaltas());
				if(!emailService.sendEmail(aluno.getUsuario().getEmail(), emailSubject, emailBody))
					notSentEmails.add(aluno.getUsuario().getEmail());
			} else {
				notSentEmails.add(aluno.getUsuario().getEmail());
			}
		}
		
		//retorna os e-mails dos alunos para os quais não foi enviado
		if (notSentEmails.size() > 0) 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(notSentEmails);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
