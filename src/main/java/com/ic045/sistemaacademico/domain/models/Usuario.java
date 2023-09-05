package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private String id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "usuario")
    private Aluno aluno;

    @OneToOne(mappedBy = "usuario")
    private Professor professor;

    @OneToOne(mappedBy = "usuario")
    private CoordenadorDeCurso coordenadorDeCurso;
}