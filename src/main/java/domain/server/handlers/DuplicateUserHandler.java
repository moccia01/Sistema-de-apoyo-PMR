package domain.server.handlers;

import domain.server.exceptions.DuplicateUserException;
import io.javalin.Javalin;

public class DuplicateUserHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(DuplicateUserException.class, (e, context) -> {
            context.render("errors/duplicate_user.hbs");
        });
    }
}
