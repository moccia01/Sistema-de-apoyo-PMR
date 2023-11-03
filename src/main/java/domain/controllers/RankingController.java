package domain.controllers;

import domain.models.entities.admins.AdminDePlataforma;
import domain.models.entities.admins.rankings.*;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.repositorios.*;
import domain.server.exceptions.CriterioNotSelectedException;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RankingController extends Controller {
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
        AdminDePlataforma admin = super.adminLogueado(context);
        model.put("nombre", admin.getNombre());
        context.render("admins/rankings/rankings.hbs", model);
    }

    public void show(Context context) {
        String criterio_string = context.pathParam("criterio");
        CriterioRanking criterioRanking = this.convertToEntity(Objects.requireNonNull(criterio_string));
        GeneradorDeRankings generadorDeRankings = new GeneradorDeRankings(this.repositorioIncidentes);

        List<Entidad> entidades = generadorDeRankings.generarSegunCriterio(criterioRanking);
        for (int i = 0; i < entidades.size(); i++) {
            entidades.get(i).setIndex(i + 1);
        }

        Map<String, Object> model = new HashMap<>();
        AdminDePlataforma admin = super.adminLogueado(context);
        model.put("nombre", admin.getNombre());
        model.put("entidades", entidades);
        model.put("criterio", this.convertToText(criterio_string));
        context.render("admins/rankings/ranking.hbs", model);
    }

    public void generate(Context context) {
        String criterio_string = context.formParam("criterio_ranking");
        CriterioRanking criterioRanking = this.convertToEntity(Objects.requireNonNull(criterio_string));
        if(criterioRanking == null) {
            throw new CriterioNotSelectedException();
        }
        context.redirect("/admin/ranking/" + criterio_string);
    }

    public CriterioRanking convertToEntity(String criterio_string) {
        return switch (criterio_string) {
            case "MayorPromedioCierre" -> new MayorPromedioCierre();
            case "MayorCantidadIncidentes" -> new MayorCantidadIncidentes();
            case "MayorGradoDeImpacto" -> new MayorGradoIncidentes();
            default -> null;
        };
    }

    public String convertToText(String criterio_string) {
        return switch (criterio_string) {
            case "MayorPromedioCierre" -> "el mayor promedio de tiempo de cierre de incidentes";
            case "MayorCantidadIncidentes" -> "la mayor cantidad de incidentes reportados en la semana";
            case "MayorGradoDeImpacto" -> "el mayor grado de impacto en las problemáticas";
            default -> null;
        };
    }
}
