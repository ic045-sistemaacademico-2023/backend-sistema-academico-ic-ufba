package com.ic045.sistemaacademico.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotCreatedException extends RuntimeException {
    public NotCreatedException(String msg) {
        super(msg);
    }

}
