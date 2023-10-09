package domain.server;

import domain.Controllers.FactoryController;
import domain.Controllers.IncidenteController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        //Aca adentro se hacen todas las querys
        Server.app().get("/", ctx -> {
            //ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("kk");
        });

        Server.app().routes( () -> {
            get("incidentes", ((IncidenteController) FactoryController.controller("incidentes"))::index);
            get("incidentes/crear", ((IncidenteController) FactoryController.controller("incidentes"))::create);
            get("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::show);
            get("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("incidentes"))::edit);
            post("incidentes", ((IncidenteController) FactoryController.controller("incidentes"))::save);
            post("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::update); // no es put pq form de html no acepta put
            delete("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::delete);

            // TODO: ACA VAN EL RESTO DE LAS RUTAS
        });
    }
}
