package domain.controllers;
import domain.models.entities.comunidad.*;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.repositorios.*;
import domain.server.exceptions.InvalidPrestacionException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.*;

public class IncidenteController extends Controller implements WithSimplePersistenceUnit {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioPrestaciones repositorioPrestaciones;
    private RepositorioServicios repositorioServicios;
    private RepositorioEstablecimientos repositorioEstablecimientos;
    private RepositorioEntidades repositorioEntidades;

    public IncidenteController(RepositorioIncidentes repositorioIncidentes,
                               RepositorioComunidades repositorioComunidades,
                               RepositorioPrestaciones repositorioPrestaciones,
                               RepositorioServicios repositorioServicios,
                               RepositorioEstablecimientos repositorioEstablecimientos,
                               RepositorioEntidades repositorioEntidades){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioIncidentes = repositorioIncidentes;
        this.repositorioPrestaciones = repositorioPrestaciones;
        this.repositorioServicios = repositorioServicios;
        this.repositorioEstablecimientos = repositorioEstablecimientos;
        this.repositorioEntidades = repositorioEntidades;
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        List<Comunidad> comunidades = this.repositorioComunidades.obtenerComunidadesDe(usuario.getId(), entityManager);

        // le setteo a los incidentes el id de su comunidad para usar en hbs
        for (Comunidad comunidad : comunidades) {
            comunidad.getIncidentes().forEach(i -> i.setComunidadId(comunidad.getId()));
        }

        model.put("comunidades", comunidades);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidentes.hbs", model);
    }

    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);

        model.put("entidades", this.repositorioEntidades.obtenerEntidades(entityManager));
        model.put("establecimientos", this.repositorioEstablecimientos.obtenerEstablecimientos(entityManager));
        model.put("servicios", this.repositorioServicios.obtenerServicios(entityManager));
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidente.hbs", model);
    }

    public void save(Context context) {
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        PrestacionDeServicio prestacionDeServicio = this.obtenerPrestacion(context, entityManager);
        beginTransaction();
        usuario.generarIncidente(context.formParam("titulo"), prestacionDeServicio, context.formParam("descripcion"));
        entityManager.merge(usuario);
        commitTransaction();
        context.redirect("/incidentes");
    }

    public void close(Context context) {
        String id = context.pathParam("incidente_id");
        String comunidad_id = context.pathParam("comunidad_id");
        EntityManager entityManager = entityManager();
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(Objects.requireNonNull(comunidad_id)), entityManager);
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        beginTransaction();
        usuario.cerrarIncidente(comunidad, incidente);
        commitTransaction();
        context.redirect("/incidentes");
    }

    private PrestacionDeServicio obtenerPrestacion(Context context, EntityManager entityManager) {
        PrestacionDeServicio prestacionDeServicio;
        try {
            beginTransaction();
            prestacionDeServicio = this.repositorioPrestaciones.obtenerPrestacion(
                    Long.parseLong(Objects.requireNonNull(context.formParam("entidad"))),
                    Long.parseLong(Objects.requireNonNull(context.formParam("establecimiento"))),
                    Long.parseLong(Objects.requireNonNull(context.formParam("servicio"))),
                    entityManager);
            commitTransaction();
        } catch(NoResultException e) {
            rollbackTransaction();
            throw new InvalidPrestacionException();
        }

        return prestacionDeServicio;
    }

}