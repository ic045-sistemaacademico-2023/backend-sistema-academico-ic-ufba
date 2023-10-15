package com.ic045.sistemaacademico.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private String message;

    public BadRequestException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
