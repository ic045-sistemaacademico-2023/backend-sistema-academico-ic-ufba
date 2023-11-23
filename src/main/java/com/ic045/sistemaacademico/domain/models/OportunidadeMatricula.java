package com.ic045.sistemaacademico.domain.models;

import java.sql.Timestamp;

import org.hibernate.type.NumericBooleanConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "oportunidade_matricula")
public class OportunidadeMatricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NonNull
	@Column(name = "nome")
	private String nome;
	
	@NonNull
	@Column(name = "descricao")
	private String descricao;
	
	@NonNull
	@Column(name = "data_inicial")
	private Timestamp dataInicial;
	
	@NonNull
	@Column(name = "data_final")
	private Timestamp dataFinal;
	
	@NonNull
	@Column(name = "aberta", columnDefinition = "tinyint default 0")
	@Convert(converter = NumericBooleanConverter.class)
	private Boolean aberta;
	
	@NonNull
	@ManyToOne
    @JoinColumn(name = "id_coordenador", referencedColumnName = "id")
    private CoordenadorDeCurso coordenador;
}
