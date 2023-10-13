package domain.controllers;

import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

public class OrganismoDeControlController extends Controller{
        public void show(Context context) {
            context.render("cargaDeDatos/cargaOrganismos.hbs");
        }
}

