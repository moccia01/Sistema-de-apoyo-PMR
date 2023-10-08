package domain.server;

import domain.Controllers.FactoryController;
import domain.Controllers.IncidenteController;

public class Router {

    public static void init() {
        //Aca adentro se hacen todas las querys
        Server.app().get("/", ctx -> {
            //ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("kk");
        });

        Server.app().get("/incidentes", ((IncidenteController) FactoryController.controller("incidentes"))::index);
    }
}
