package domain.controllers;

import domain.models.repositorios.RepositorioComunidades;
import domain.models.repositorios.RepositorioCredenciales;
import domain.models.repositorios.RepositorioIncidentes;
import domain.models.repositorios.RepositorioUsuarios;

public class FactoryController {

    public static Object controller(String nombre){

        return switch (nombre) {
            case "incidentes" -> new IncidenteController(new RepositorioIncidentes());
            case "comunidades" -> new ComunidadController(new RepositorioComunidades());
            case "login" -> new LoginController(new RepositorioUsuarios(), new RepositorioCredenciales());
            default -> null;
        };
    }
}
