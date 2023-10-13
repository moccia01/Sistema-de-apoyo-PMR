package domain.controllers;

import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;

public class EntidadPrestadoraController  extends Controller {
    public void show(Context context) {
        context.render("cargaDeDatos/cargaEntidades.hbs");
    }
}
