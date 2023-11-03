package domain.server.handlers;

import domain.server.exceptions.DuplicateUserException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class DuplicateUserHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(DuplicateUserException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error_return", context.sessionAttribute("error_return"));
            model.put("nombre", context.formParam("nombre"));
            model.put("apellido", context.formParam("apellido"));
            context.render("errors/duplicate_user.hbs", model);
        });
    }
}
