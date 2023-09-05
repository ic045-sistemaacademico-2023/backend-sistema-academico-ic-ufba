package com.ic045.sistemaacademico.domain.models;

import jakarta.persistence.*;

@Entity(name = "administrador")
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    private Usuario usuario;

    private String nome;

    private String email;

    private String senha;

}
