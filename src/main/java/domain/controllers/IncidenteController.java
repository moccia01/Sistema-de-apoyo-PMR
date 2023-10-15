package domain.controllers;
import domain.models.entities.comunidad.*;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.*;
import domain.server.utils.ICrudViewsHandler;


import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioServicios repositorioServicios;
    private RepositorioEstablecimientos repositorioEstablecimientos;
    private RepositorioEntidades repositorioEntidades;

    public IncidenteController(RepositorioIncidentes repositorioIncidentes,
                               RepositorioServicios repositorioServicios,
                               RepositorioEstablecimientos repositorioEstablecimientos,
                               RepositorioEntidades repositorioEntidades,
                               RepositorioComunidades repositorioComunidades){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioIncidentes = repositorioIncidentes;
        this.repositorioServicios = repositorioServicios;
        this.repositorioEstablecimientos = repositorioEstablecimientos;
        this.repositorioEntidades = repositorioEntidades;
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidades = this.repositorioComunidades.obtenerComunidadesDe(super.usuarioLogueado(context).getId());
        for (Comunidad comunidad : comunidades) {
            comunidad.getIncidentes().forEach(i -> i.setComunidadId(comunidad.getId()));
        }
        model.put("comunidades", comunidades);
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
        super.usuarioLogueado(context).generarIncidente(
                context.formParam("titulo"),
                this.obtenerPrestacion(context),
                context.formParam("descripcion"));
        context.redirect("/incidentes");
    }

    @Override
    public void edit(Context context) {
        String id = context.pathParam("id");
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        Map<String, Object> model = new HashMap<>();
        model.put("entidades", new RepositorioEntidades().obtenerEntidades());
        model.put("establecimientos", new RepositorioEstablecimientos().obtenerEstablecimientos());
        model.put("servicios", new RepositorioServicios().obtenerServicios());
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
        //TODO ver de hacer con form a ver si no hay q apretar 2 veces para q funque
        String id = context.pathParam("id");
        String comunidad_id = context.queryParam("comunidad_id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(Objects.requireNonNull(comunidad_id)));
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id));
        Usuario usuario = super.usuarioLogueado(context);
        usuario.cerrarIncidente(comunidad, incidente);
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

        PrestacionDeServicio prestacionDeServicio = this.obtenerPrestacion(contexto);
        incidente.setPrestacionDeServicio(prestacionDeServicio);

        incidente.setEstado(false);
        incidente.setFechaHoraApertura(LocalDateTime.now());

        Usuario usuario = super.usuarioLogueado(contexto);
        incidente.setUsuarioApertura(usuario);
    }

    private PrestacionDeServicio obtenerPrestacion(Context context) {
        PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio();

        prestacionDeServicio.setServicio(this.repositorioServicios.obtenerServicio(
                Long.parseLong(Objects.requireNonNull(context.formParam("servicio")))));
        prestacionDeServicio.setEstablecimiento(this.repositorioEstablecimientos.obtenerEstablecimiento(
                Long.parseLong(Objects.requireNonNull(context.formParam("establecimiento")))));
        prestacionDeServicio.setEntidad(this.repositorioEntidades.obtenerEntidad(
                Long.parseLong(Objects.requireNonNull(context.formParam("entidad")))));
        return prestacionDeServicio;
    }

}