package domain.server.handlers;

import domain.server.exceptions.FileNotLoadedException;
import io.javalin.Javalin;

public class FileNotLoadedHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(FileNotLoadedException.class, (e, context) -> {
            context.render("errors/file_not_loaded.hbs");
        });
    }
}
