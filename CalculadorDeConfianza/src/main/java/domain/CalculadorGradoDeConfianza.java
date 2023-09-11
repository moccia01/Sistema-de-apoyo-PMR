package domain;

import java.util.function.Consumer;

import domain.controllers.ActualizacionGradoConfianzaController;
import domain.repositorios.RepoComunidades;
import domain.repositorios.RepoUsuarios;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

// TODO FALTA MAPPEAR CON BD COMO EN EL SISTEMA GENERAL, LAS QUERYS A LA BD EN LOS REPOS Y TESTEAR
public class CalculadorGradoDeConfianza {
    
    public static void main(String[] args) {
        //RepoUsuarios repoUsuarios = new RepoUsuarios();     //TODO Instanciar bien
        RepoComunidades repoComunidades = new RepoComunidades();

        Integer port = Integer.parseInt(System.getProperty("port", "8080"));
        Javalin app = Javalin.create(config()).start(port);

        //app.post("/api/actualizacion", new ActualizacionGradoConfianzaController(repoUsuarios, repoComunidades)); //TODO Instanciar bien
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
