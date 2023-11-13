package com.ic045.sistemaacademico.security;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ic045.sistemaacademico.domain.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                    .withIssuer("sistemaAcademico")
                    .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(24 * 60 * 60 * 1000)) // TO DO: tempo de expiração do Token
                .withSubject(usuario.getId().toString()) // TO DO: o que vai ser passado no token
                .withClaim("ROLE", usuario.getRole().toString())
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm).withIssuer("sistemaAcademico").build().verify(token); // TO DO: verificação do tempo de expiração
    }
}
