package domain.controllers;
import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.RepositorioEntidades;
import domain.models.repositorios.RepositorioEstablecimientos;
import domain.models.repositorios.RepositorioIncidentes;
import domain.models.repositorios.RepositorioServicios;
import domain.server.utils.ICrudViewsHandler;


import io.javalin.http.Context;

import java.time.LocalDateTime;
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
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("entidades", new RepositorioEntidades().obtenerEntidades());
        model.put("establecimientos", new RepositorioEstablecimientos().obtenerEstablecimientos());
        model.put("servicios", new RepositorioServicios().obtenerServicios());
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
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }

    @Override
    public void update(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        this.asignarParametros(incidente, context);
        this.repositorioIncidentes.modificar(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        this.repositorioIncidentes.eliminar(incidente);
        context.redirect("/incidentes");
    }

    public void close(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        Usuario usuario = super.usuarioLogueado(context);
        incidente.cerrar();
        incidente.setUsuarioCierre(usuario);
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context contexto){
        // podria ser un factory/builder (?)
        if(!Objects.equals(contexto.formParam("titulo"), "")) {
            incidente.setTitulo(contexto.formParam("titulo"));
        }
        if(!Objects.equals(contexto.formParam("descripcion"), "")) {
            incidente.setDescripcion(contexto.formParam("descripcion"));
        }

        // TODO implementar asignaciones para el resto de los inputs
        // si recibimos un id tipo entidad_id hay que instanciar un repo de entidades y buscarlo por id en la bd
        // y asi con todos los inputs del form




        incidente.setEstado(false);
        incidente.setFechaHoraApertura(LocalDateTime.now());

        Usuario usuario = super.usuarioLogueado(contexto);
        incidente.setUsuarioApertura(usuario);
    }

}