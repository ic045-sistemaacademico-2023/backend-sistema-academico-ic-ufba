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

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota nota = service.findById(id);

        return nota != null ? ResponseEntity.ok(nota): ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/aluno")
    public ResponseEntity<Aluno> findAlunoByNota(@PathVariable Long id){
        Nota nota = service.findById(id);
        Aluno aluno = nota.getAluno();
        return aluno != null ? ResponseEntity.ok(aluno): ResponseEntity.notFound().build();
    }

    @GetMapping("/aluno/{id}")
    public List<Nota> findByAlunoId(@PathVariable Long id){
    	return service.findByAlunoId(id);
    }

    @PostMapping("/")
    public ResponseEntity<Nota> insertNota(@RequestBody InsertNotaRequest insertNotaRequest){
    	if(insertNotaRequest.nota() == null)
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
     * Envio de e-mail de notas
     */
    
    private String buildStudentGradeEmail(Aluno aluno, Turma turma, Nota nota) {
    	String emailBody= String.format("%s, sua nota em %s foi %.2f com %.2f faltas.", aluno.getNome(),
				turma.getDisciplina().getNome(), nota.getNota(), nota.getFaltas());
    	return emailBody;
    }
    
    @PostMapping("/enviar/aluno/{alunoId}/{turmaId}")
    public ResponseEntity<String> enviarNotaAluno(@PathVariable Long alunoId, @PathVariable Long turmaId){
    	Aluno aluno = alunoService.findById(alunoId);
    	Turma turma = turmaService.findById(turmaId);
    	Nota nota = service.findByAlunoAndTurma(aluno, turma);
		String emailBody = buildStudentGradeEmail(aluno, turma, nota);
		return ResponseEntity.status(HttpStatus.OK).body(emailBody);
		//return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PostMapping("/enviar/turma/{turmaId}")
    public ResponseEntity<List<String>> enviarNotasAlunosTurma(@PathVariable Long turmaId){
    	Turma turma = turmaService.findById(turmaId);
    	Nota nota;
    	List<String> emailBodies = new ArrayList<String>();
    	for(Aluno aluno : turma.getAlunos()) {
    		nota = service.findByAlunoAndTurma(aluno, turma);
    		emailBodies.add(buildStudentGradeEmail(aluno, turma, nota));
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(emailBodies);
		//return ResponseEntity.status(HttpStatus.OK).build();;
    }
    

}
