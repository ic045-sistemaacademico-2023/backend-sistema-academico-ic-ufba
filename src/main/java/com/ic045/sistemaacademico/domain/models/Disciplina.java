package com.ic045.sistemaacademico.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "id_curso", referencedColumnName = "id")
	private Curso curso;

	@NonNull
	private String nome;

	@NonNull
	private String codigo;

	@NonNull
	private String ementa;

	@NonNull
	@Column(name = "pre_requisitos")
	private String preRequisitos;

	@NonNull
	private String area;

	private String observacao;

	@Column(name = "ch_total", nullable = false)
	private int chTotal;

	@Column(name = "ch_teorica", nullable = false)
	private int chTeorica;

	@Column(name = "ch_pratica", nullable = false)
	private int chPratica;

	private String bibliografia;

	@OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Nota> notas = new ArrayList<>();

}
