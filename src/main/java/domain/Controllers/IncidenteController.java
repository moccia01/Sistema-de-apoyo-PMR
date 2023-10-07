package domain.Controllers;
import domain.models.entities.comunidad.Incidente;
import domain.models.repositorios.RepositorioIncidentes;
import domain.server.utils.ICrudViewsHandler;


import io.javalin.http.HttpStatus;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes RepositorioIncidentes;

    public IncidenteController(RepositorioIncidentes RepositorioIncidentes){
        this.RepositorioIncidentes = RepositorioIncidentes;
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> Incidentes = this.RepositorioIncidentes.obtenerIncidentes();
        model.put("Incidentes", Incidentes);
        context.render("templates/Incidentes.hbs", model);
    }

}