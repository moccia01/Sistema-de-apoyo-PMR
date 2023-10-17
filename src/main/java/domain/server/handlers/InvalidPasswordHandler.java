package domain.server.handlers;

import domain.server.exceptions.DuplicateUserException;
import domain.server.exceptions.InvalidPasswordException;
import io.javalin.Javalin;

public class InvalidPasswordHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPasswordException.class, (e, context) -> {
            context.render("errors/invalid_password.hbs");
        });
    }
}
