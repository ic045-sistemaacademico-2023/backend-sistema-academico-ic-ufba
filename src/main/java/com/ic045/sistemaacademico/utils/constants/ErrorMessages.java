package com.ic045.sistemaacademico.utils.constants;

public enum ErrorMessages {
    GENERIC_ERROR("An unexpected error has occured."),
    OBJECT_NOT_FOUND("The %s with id: %s – Couldn't be found"),
    USUARIO_CPF_NOT_FOUND("The Usuario with cpf: %s – Couldn't be found"),
    INVALID_INPUT("Invalid input."),
    NOT_CREATED("Existing data"),
    DATA_NULL("Null data found"),
    COULD_NOT_UPDATE("The %s with id %s - Couldn't be updated"),
    OBJECT_NOT_FOUND_GENERIC_PROP("The %s with %s: %s – Couldn't be found"),
	EMAIL_NOT_SENT("It was not possible to send the email");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
