package domain.server;

import domain.server.handlers.AppHandlers;
import domain.server.middlewares.AuthMiddleware;
import io.javalin.Javalin;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Server {
    public static final String baseUrl = "localhost:8080";
    private static Javalin app = null;
    public static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static Javalin app() {
        if(app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static EntityManager entityManager() {
        if(entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void init() {
        if(app == null) {
            entityManagerFactory =  createEntityManagerFactory();
            Integer port = Integer.parseInt(System.getProperty("PORT", "8080"));
            app = Javalin.create(config()).start(port);
            initTemplateEngine();
            AppHandlers.applyHandlers(app);
            new Router().init();

        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/public";  // Con esto sabe el navegador donde buscar los css, js, etc
            });
            AuthMiddleware.apply(config);
        };
    }


    private static void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> { // Función que renderiza el template
                    Handlebars handlebars = new Handlebars();
                    Template template = null;
                    try {
                        template = handlebars.compile(
                                "templates/" + path.replace(".hbs",""));
                        return template.apply(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        // https://stackoverflow.com/questions/8836834/read-environment-variables-in-persistence-xml-file
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[] { "javax.persistence.jdbc.url", "javax.persistence.jdbc.user",
                "javax.persistence.jdbc.password", "javax.persistence.jdbc.driver", "hibernate.hbm2ddl.auto",
                "hibernate.connection.pool_size", "hibernate.show_sql", "hibernate.dialect" };

        for (String key : keys) {
            if (env.containsKey(key)) {
                String value = env.get(key);
                System.out.println(key + ": " + value);
                configOverrides.put(key, value);

            }
        }

        return Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
    }

}
