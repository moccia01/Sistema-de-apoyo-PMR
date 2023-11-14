package domain.server.handlers;

import domain.server.exceptions.AccessDeniedException;
import domain.server.exceptions.InvalidPrestacionException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class InvalidPrestacionHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPrestacionException.class, (e, context) -> {
            context.render("errors/invalid_prestacion.hbs");
        });
    }
}
