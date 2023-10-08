package domain.Controllers;

import domain.models.repositorios.RepositorioIncidentes;

public class FactoryController {

    public static Object controller(String nombre){
        Object controller = null;

        switch(nombre) {
            case "incidentes" : controller = new IncidenteController(new RepositorioIncidentes()); break;

        }
        return controller;
    }
}
