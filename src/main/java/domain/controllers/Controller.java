package domain.controllers;

import domain.models.entities.comunidad.Usuario;
import domain.server.exceptions.AccessDeniedException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

public abstract class Controller implements WithSimplePersistenceUnit {

    protected Usuario usuarioLogueado(Context ctx) {
        if(ctx.sessionAttribute("usuario_id") == null) {
            throw new AccessDeniedException();
        }
        return entityManager()
                .find(Usuario.class, Long.parseLong(ctx.sessionAttribute("usuario_id")));
    }
}