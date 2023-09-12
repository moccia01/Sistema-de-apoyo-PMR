package domain;

import java.util.function.Consumer;

import domain.controllers.ActualizacionGradoConfianzaController;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

// TODO FALTA MAPPEAR CON BD COMO EN EL SISTEMA GENERAL, LAS QUERYS A LA BD EN LOS REPOS Y TESTEAR
public class CalculadoraGradoApp {
    
    public static void main(String[] args) {
        Integer port = Integer.parseInt(System.getProperty("port", "8080"));
        Javalin app = Javalin.create(config()).start(port);

        app.get("/", ctx -> ctx.result("Hola Mundo"));
        app.post("/api/actualizacion", new ActualizacionGradoConfianzaController());
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/public";
            });
        };
    }
}
