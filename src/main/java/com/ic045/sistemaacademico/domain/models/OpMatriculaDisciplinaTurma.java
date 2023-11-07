package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "opmatricula_disciplina_turma", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id_oportunidade_matricula", "id_disciplina", "id_turma" }) })
public class OpMatriculaDisciplinaTurma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NonNull
	@ManyToOne(cascade = {CascadeType.REMOVE})
	@JoinColumn(name = "id_oportunidade_matricula", referencedColumnName = "id")
	private OportunidadeMatricula oportunidadeMatricula;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "id_disciplina", referencedColumnName = "id")
	private Disciplina disciplina;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "id_turma", referencedColumnName = "id")
	private Turma turma;
	
}
