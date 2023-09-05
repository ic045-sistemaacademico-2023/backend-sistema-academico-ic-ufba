package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "senha",nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private String status;

    /*Extra
    * as outras tabelas devem buscar o usuario
    * */
//    @OneToOne(mappedBy = "usuario")
//    private Aluno aluno;
//
//    @OneToOne(mappedBy = "usuario")
//    private Professor professor;
//
//    @OneToOne(mappedBy = "usuario")
//    private CoordenadorDeCurso coordenadorDeCurso;
}