package com.ic045.sistemaacademico.domain.models;

public class Role {

    public enum Type{
        ADMIN,
        COORDENADOR_DE_CURSO,
        PROFESSOR,
        ALUNO,
    }
    public enum Status{
        EMAIL_CHECK,
        WAITING_APPROVAL,
        APPROVED,
        DENIED
    }
    public enum Shift{
        MATUTINO,
        VESPERTINO,
        NOTURNO
    }
}
