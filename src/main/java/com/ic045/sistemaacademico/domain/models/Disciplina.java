package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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

    private String codigo;

		@NonNull
		private String nome;

    @NonNull
    private String ementa;

    @Column(name = "pre_requisitos")
    private String preRequisitos;

    @NonNull
    private String area;

    private String observacao;

    @NonNull
    @Column(name = "ch_pratica")
    private int chPratica;

    @NonNull
    @Column(name = "ch_teorica")
    private int chTeorica;

		@NonNull
		@Column(name = "ch_total")
		private int chTotal;

    @NonNull
    private int semestre;

    private String objetivos;

    private String conteudo;

    private String bibliografia;
}
