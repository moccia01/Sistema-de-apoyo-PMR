package domain.controllers;

import domain.models.entities.cargaDeDatos.LectorArchivoCSV;
import domain.models.repositorios.RepositorioEntidadesPrestadoras;
import domain.models.repositorios.RepositorioOrganismoDeControl;
import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

public class OrganismoDeControlController extends Controller{
    private LectorArchivoCSV lectorDeArchivo;
    private RepositorioOrganismoDeControl repositorioOrganismoDeControl;

        public void show(Context context) {
            context.render("cargaDeDatos/cargaOrganismos.hbs");
        }



}

