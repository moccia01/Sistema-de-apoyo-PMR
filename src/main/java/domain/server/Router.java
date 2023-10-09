package domain.server;

import domain.Controllers.ComunidadController;
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

            get("comunidades",((ComunidadController) FactoryController.controller("comunidades"))::index);
            get("comunidad/crear", ((ComunidadController) FactoryController.controller("comunidades"))::create);
            get("comunidad/{id}", ((ComunidadController) FactoryController.controller("comunidades"))::show);
            get("comunidad/{id}/editar", ((ComunidadController) FactoryController.controller("comunidades"))::edit);
            post("comunidad", ((ComunidadController) FactoryController.controller("comunidades"))::save);
            post("comunidad/{id}", ((ComunidadController) FactoryController.controller("comunidades"))::update); // no es put pq form de html no acepta put
            delete("comunidad/{id}", ((ComunidadController) FactoryController.controller("comunidades"))::delete);
            // TODO: ACA VAN EL RESTO DE LAS RUTAS
        });
    }
}
