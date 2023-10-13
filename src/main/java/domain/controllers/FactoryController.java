package domain.controllers;

import domain.models.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre){

        return switch (nombre) {
            case "incidentes" -> new IncidenteController(new RepositorioIncidentes());
            case "comunidades" -> new ComunidadController(new RepositorioComunidades(), new RepositorioUsuarios());
            case "login" -> new LoginController(new RepositorioUsuarios(), new RepositorioCredenciales(), new RepositorioTiemposConfiguracion(), new RepositorioGradosDeConfianza());
            default -> null;
        };
    }
}
