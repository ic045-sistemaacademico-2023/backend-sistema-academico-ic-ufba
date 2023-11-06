package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.controller.vos.request.LoginUsuario;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenProvider provider;

    public String login(final LoginUsuario request) {
        Usuario usuario = usuarioService.findByCpf(request.cpf());

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new RuntimeException();
        }

        return provider.generateToken(usuario);
    }
}
