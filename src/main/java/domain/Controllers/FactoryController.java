package domain.Controllers;

import domain.models.repositorios.RepositorioComunidades;
import domain.models.repositorios.RepositorioIncidentes;

public class FactoryController {

    public static Object controller(String nombre){
        Object controller = null;

        switch(nombre) {
            case "incidentes" : controller = new IncidenteController(new RepositorioIncidentes()); break;
            case "comunidades" : controller = new ComunidadController(new RepositorioComunidades()); break;
        }
        return controller;
    }
}
