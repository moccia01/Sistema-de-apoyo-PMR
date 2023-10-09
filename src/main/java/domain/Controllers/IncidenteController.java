package domain.Controllers;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.RepositorioIncidentes;
import domain.server.utils.ICrudViewsHandler;


import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;

    public IncidenteController(RepositorioIncidentes RepositorioIncidentes){
        this.repositorioIncidentes = RepositorioIncidentes;
    }

    public void index(Context context) {
        //TODO ver como hacer la paginacion
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidentes.obtenerIncidentes();
        model.put("incidentes", incidentes);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("incidentes", null);
        context.render("incidentes/incidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioIncidentes.agregar(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void edit(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Integer.parseInt(id));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }

    @Override
    public void update(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Integer.parseInt(id));
        this.asignarParametros(incidente, context);
        this.repositorioIncidentes.modificar(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametros(Incidente incidente, Context contexto){
        // podria ser un factory/builder (?)
        if(!Objects.equals(contexto.formParam("titulo"), "")) {
            incidente.setTitulo(contexto.formParam("titulo"));
        }
        // TODO implementar asignaciones para el resto de los inputs
        // si recibimos un id tipo entidad_id hay que instanciar un repo de entidades y buscarlo por id en la bd
        // y asi con todos los inputs del form

        incidente.setEstado(false);

        // TODO ver como setear usuario apertura preguntandole al contexto (?) (por ahora lo hago asi para que no rompa
        Usuario usuario = new Usuario();
        usuario.setCredencialDeAcceso(new CredencialDeAcceso());
        usuario.setMedioConfigurado(new MensajeWhatsApp());
        usuario.setTiempoConfigurado(new CuandoSucede());
        GradoDeConfianza grado = new GradoDeConfianza();
        grado.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        usuario.setGradoDeConfianza(grado);
        usuario.setPuntosDeConfianza(6);

        incidente.setUsuarioApertura(usuario);
    }

}