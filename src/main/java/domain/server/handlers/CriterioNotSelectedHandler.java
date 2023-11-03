package domain.server.handlers;

import domain.server.exceptions.AccessDeniedException;
import domain.server.exceptions.CriterioNotSelectedException;
import io.javalin.Javalin;

public class CriterioNotSelectedHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(CriterioNotSelectedException.class, (e, context) -> {
            context.render("errors/criterio_not_selected.hbs");
        });
    }

}
