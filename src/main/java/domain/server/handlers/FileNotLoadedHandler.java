package domain.server.handlers;

import domain.server.exceptions.FileNotLoadedException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class FileNotLoadedHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(FileNotLoadedException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error_return", context.sessionAttribute("error_return"));
            context.render("errors/file_not_loaded.hbs", model);
        });
    }
}
