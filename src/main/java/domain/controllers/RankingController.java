package domain.controllers;

import domain.models.entities.comunidad.Comunidad;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.rankings.CierreIncidentes;
import domain.models.entities.rankings.CriterioRanking;
import domain.models.entities.rankings.MayorCantidadIncidentes;
import domain.models.repositorios.*;
import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioServicios repositorioServicios;
    private RepositorioEstablecimientos repositorioEstablecimientos;
    private RepositorioEntidades repositorioEntidades;

    public RankingController(RepositorioIncidentes repositorioIncidentes,
                             RepositorioServicios repositorioServicios,
                             RepositorioEstablecimientos repositorioEstablecimientos,
                             RepositorioEntidades repositorioEntidades,
                             RepositorioComunidades repositorioComunidades) {
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioIncidentes = repositorioIncidentes;
        this.repositorioServicios = repositorioServicios;
        this.repositorioEstablecimientos = repositorioEstablecimientos;
        this.repositorioEntidades = repositorioEntidades;
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = repositorioIncidentes.obtenerIncidentes();
        model.put("incidentes", incidentes);
        context.render("rankings/rankings.hbs", model);
    }

    public void show(Context context) {
        context.render("rankings/rankings.hbs");
    }
   /* public void calcularRanking(Context context){
        String criterio = context.queryParam("criterio");

        if (criterio != null) {
            // Instanciar el criterio correspondiente.
            CriterioRanking criterioRanking;

            if (criterio.equals("MayorCantidadIncidentes")) {
                criterioRanking = new MayorCantidadIncidentes();
            } else if (criterio.equals("CierreIncidentes")) {
                criterioRanking = new CierreIncidentes();
            } else {
                // Manejar el caso cuando no se selecciona un criterio válido.
                context.result("Criterio no válido.");
                return;
            }
            //TODO arreglar calcularValor para el criterioRanking
            //double valorRanking = criterioRanking.calcularValor(repositorioIncidentes.obtenerIncidentes());

            //context.result("El valor del ranking es: " + valorRanking);
        } else {
            context.result("No se seleccionó un criterio.");
        }}*/

    public void create(Context context) {

    }

    public void save(Context context) {

    }

    public void edit(Context context) {

    }

    public void update(Context context) {

    }

    public void delete(Context context) {

    }
}
