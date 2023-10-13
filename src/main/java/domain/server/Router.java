package domain.server;

import domain.controllers.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().routes( () -> {
            get("/", ((LoginController) FactoryController.controller("login"))::index);
            get("login", ((LoginController) FactoryController.controller("login"))::show);
            post("login", ((LoginController) FactoryController.controller("login"))::login);
            get("registro", ((LoginController) FactoryController.controller("login"))::create);
            post("registro", ((LoginController) FactoryController.controller("login"))::save);
            get("index", (context -> context.render("/index.hbs")));

            get("/entidades_prestadoras", ((EntidadPrestadoraController) FactoryController.controller("entidad_prestadora"))::show);

            get("/organismos_de_control", ((OrganismoDeControlController) FactoryController.controller("organismo_de_control"))::show);

            get("/rankings", ((RankingController) FactoryController.controller("ranking"))::show);

            get("incidentes", ((IncidenteController) FactoryController.controller("incidentes"))::index);
            get("incidentes/crear", ((IncidenteController) FactoryController.controller("incidentes"))::create);
            get("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::show);
            get("incidentes/{id}/cerrar", ((IncidenteController) FactoryController.controller("incidentes"))::close);
            get("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("incidentes"))::edit);
            post("incidentes", ((IncidenteController) FactoryController.controller("incidentes"))::save);
            post("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::update); // no es put pq form de html no acepta put
            delete("incidentes/{id}", ((IncidenteController) FactoryController.controller("incidentes"))::delete);

            get("comunidades",((ComunidadController) FactoryController.controller("comunidades"))::index);
            get("comunidades/{id}/admin",((ComunidadController) FactoryController.controller("comunidades"))::admin);
            get("comunidades/{id}/ver",((ComunidadController) FactoryController.controller("comunidades"))::ver);


            // TODO: ACA VAN EL RESTO DE LAS RUTAS
        });
    }
}
