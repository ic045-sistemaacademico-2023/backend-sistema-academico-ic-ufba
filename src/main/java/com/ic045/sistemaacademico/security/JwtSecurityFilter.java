package com.ic045.sistemaacademico.security;

import java.io.IOException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ic045.sistemaacademico.domain.models.Usuario;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String authHeader = recoverAuthHeader(request);

        try {
            if (isJwtAuthorization(authHeader)) {
                String token = recoverBearerToken(authHeader);
                Long userId = Long.parseLong(provider.validateToken(token).getSubject());
                Usuario usuario = usuarioRepository.findById(userId).orElse(null);

                if (usuario != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, usuario.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JWTVerificationException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recoverAuthHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private String recoverBearerToken(String authHeader) {
        return authHeader.replace("Bearer ", "").trim();
    }

    private boolean isJwtAuthorization(String authorization) {
        return authorization != null && authorization.startsWith("Bearer ");
    }
}
