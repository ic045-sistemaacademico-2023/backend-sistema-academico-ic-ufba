package com.ic045.sistemaacademico.services;

import static org.junit.jupiter.api.Assertions.*;

import com.ic045.sistemaacademico.exception.custom.NotFoundException;
import com.ic045.sistemaacademico.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UsuarioServiceTest {
    @InjectMocks
    UsuarioService service;

    @Mock
    UsuarioRepository repository;

    @Test
    @DisplayName("Should throw a Not Found Exception when id doesn't exist")
    void shouldThrowNotFoundExceptionWhenIdDoesntExist() {
        Assertions.assertThrows(
                NotFoundException.class,
                () -> service.findById(131L)
        );
    }
}
