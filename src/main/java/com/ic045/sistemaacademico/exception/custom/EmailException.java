package com.ic045.sistemaacademico.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailException extends RuntimeException{
	private String message;

    public EmailException(String msg){
        super(msg);
        this.message = msg;
    }

}
