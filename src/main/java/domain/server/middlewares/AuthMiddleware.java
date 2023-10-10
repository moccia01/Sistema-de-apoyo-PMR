package domain.server.middlewares;

import domain.server.exceptions.AccessDeniedException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthMiddleware {

    public static void apply(JavalinConfig config) {
        config.accessManager((handler, context, routeRoles) -> {
            List<String> publicPaths = new ArrayList<>();
            publicPaths.add("/");
            publicPaths.add("/login");
            publicPaths.add("/registro");

            String path = context.req().getRequestURI();
            if(context.sessionAttribute("id_usuario") != null || publicPaths.contains(path)) {
                handler.handle(context);
            }
            else {
                throw new AccessDeniedException();
            }
        });
    }

}
