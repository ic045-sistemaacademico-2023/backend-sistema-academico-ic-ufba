package com.ic045.sistemaacademico.utils.helpers;

import com.ic045.sistemaacademico.controller.vos.request.InsertUsuarioRequest;
import com.ic045.sistemaacademico.domain.models.Status;
import com.ic045.sistemaacademico.domain.models.Usuario;

public abstract class UsuarioMapper {
    public static Usuario mapInsertUsuarioRequestToUsuario(InsertUsuarioRequest request) {
        return new Usuario(
                request.cpf(),
                request.nome(),
                request.email(),
                request.senha(),
                request.role(),
                Status.EMAIL_CHECK
        );
    }
}
