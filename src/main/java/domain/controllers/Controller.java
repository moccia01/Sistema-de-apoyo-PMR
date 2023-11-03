package domain.controllers;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.comunidad.Usuario;
import domain.server.Server;
import domain.server.exceptions.AccessDeniedException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

import javax.persistence.EntityManager;
import java.util.Objects;

public abstract class Controller implements WithSimplePersistenceUnit {

    protected Usuario usuarioLogueado(Context ctx) {
        if(ctx.sessionAttribute("usuario_id") == null) {
            throw new AccessDeniedException();
        }
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        return entityManager.find(Usuario.class, Objects.requireNonNull(ctx.sessionAttribute("usuario_id")));
    }

    protected AdminDePlataforma adminLogueado(Context ctx) {
        if(ctx.sessionAttribute("admin_id") == null) {
            throw new AccessDeniedException();
        }
        EntityManager entityManager = Server.entityManagerFactory.createEntityManager();
        return entityManager.find(AdminDePlataforma.class, ctx.sessionAttribute("admin_id"));
    }

}