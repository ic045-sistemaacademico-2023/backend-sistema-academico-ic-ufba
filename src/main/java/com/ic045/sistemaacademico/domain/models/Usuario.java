package com.ic045.sistemaacademico.domain.models;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
    private Role.Type role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Role.Status status;

    public Usuario(final String cpf, final String nome, final String email, final String senha, final Role.Type role, final Role.Status status) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN ->
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_COORDENADOR_DE_CURSO"),
                        new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                        new SimpleGrantedAuthority("ROLE_ALUNO")
                );

            case COORDENADOR_DE_CURSO ->
                List.of(
                        new SimpleGrantedAuthority("ROLE_COORDENADOR_DE_CURSO"),
                        new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                        new SimpleGrantedAuthority("ROLE_ALUNO")
                );

            case PROFESSOR ->
                List.of(
                        new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                        new SimpleGrantedAuthority("ROLE_ALUNO")
                );

            case ALUNO ->
                List.of(
                        new SimpleGrantedAuthority("ROLE_ALUNO")
                );
        };
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
