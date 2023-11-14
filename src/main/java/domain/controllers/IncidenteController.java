package domain.controllers;
import domain.models.entities.comunidad.*;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.models.repositorios.*;
import domain.server.Server;
import domain.server.exceptions.InvalidPrestacionException;
import domain.server.utils.ICrudViewsHandler;

import io.javalin.http.Context;

import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.*;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioServicios repositorioServicios;
    private RepositorioEstablecimientos repositorioEstablecimientos;
    private RepositorioEntidades repositorioEntidades;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioPrestaciones repositorioPrestaciones;

    public IncidenteController(RepositorioIncidentes repositorioIncidentes,
                               RepositorioServicios repositorioServicios,
                               RepositorioEstablecimientos repositorioEstablecimientos,
                               RepositorioEntidades repositorioEntidades,
                               RepositorioComunidades repositorioComunidades,
                               RepositorioUsuarios repositorioUsuarios,
                               RepositorioPrestaciones repositorioPrestaciones){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioIncidentes = repositorioIncidentes;
        this.repositorioServicios = repositorioServicios;
        this.repositorioEstablecimientos = repositorioEstablecimientos;
        this.repositorioEntidades = repositorioEntidades;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioPrestaciones = repositorioPrestaciones;
    }

    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = Server.entityManager();
        List<Comunidad> comunidades = this.repositorioComunidades.obtenerComunidadesDe(super.usuarioLogueado(context, entityManager).getId(), entityManager);
        for (Comunidad comunidad : comunidades) {
            comunidad.getIncidentes().forEach(i -> i.setComunidadId(comunidad.getId()));
        }
        model.put("comunidades", comunidades);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidentes.hbs", model);
    }

    @Override
    public void show(Context context) {
        String id = context.pathParam("id");
        EntityManager entityManager = Server.entityManager();
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidente.hbs", model);
    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = Server.entityManager();
        model.put("entidades", new RepositorioEntidades().obtenerEntidades(entityManager));
        model.put("establecimientos", new RepositorioEstablecimientos().obtenerEstablecimientos(entityManager));
        model.put("servicios", new RepositorioServicios().obtenerServicios(entityManager));
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        EntityManager entityManager = Server.entityManager();
        EntityTransaction tx = entityManager.getTransaction();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        PrestacionDeServicio prestacionDeServicio = this.obtenerPrestacion(context, entityManager);
        tx.begin();
        usuario.generarIncidente(
                context.formParam("titulo"),
                prestacionDeServicio,
                context.formParam("descripcion"));
        this.repositorioUsuarios.modificar(usuario, entityManager);
        tx.commit();
        context.redirect("/incidentes");
    }

    @Override
    public void edit(Context context) {
        String id = context.pathParam("id");
        EntityManager entityManager = Server.entityManager();
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
        Map<String, Object> model = new HashMap<>();
        model.put("entidades", new RepositorioEntidades().obtenerEntidades(entityManager));
        model.put("establecimientos", new RepositorioEstablecimientos().obtenerEstablecimientos(entityManager));
        model.put("servicios", new RepositorioServicios().obtenerServicios(entityManager));
        model.put("incidente", incidente);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/incidentes/incidente.hbs", model);
    }

    @Override
    public void update(Context context) {
        String id = context.pathParam("id");
        EntityManager entityManager = Server.entityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
            this.asignarParametros(incidente, context, entityManager);
            this.repositorioIncidentes.modificar(incidente, entityManager);
        tx.commit();
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {
        String id = context.pathParam("id");
        EntityManager entityManager = Server.entityManager();
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
        this.repositorioIncidentes.eliminar(incidente, entityManager);
        context.redirect("/incidentes");
    }

    public void close(Context context) {
        String id = context.pathParam("incidente_id");
        String comunidad_id = context.pathParam("comunidad_id");
        EntityManager entityManager = Server.entityManager();
        EntityTransaction tx = entityManager.getTransaction();;
        tx.begin();
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(Objects.requireNonNull(comunidad_id)), entityManager);
        Incidente incidente = this.repositorioIncidentes.obtenerIncidente(Long.parseLong(id), entityManager);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        usuario.cerrarIncidente(comunidad, incidente);
        tx.commit();
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context, EntityManager entityManager){
        // podria ser un factory/builder (?)
        if(!Objects.equals(context.formParam("titulo"), "")) {
            incidente.setTitulo(context.formParam("titulo"));
        }
        if(!Objects.equals(context.formParam("descripcion"), "")) {
            incidente.setDescripcion(context.formParam("descripcion"));
        }

        PrestacionDeServicio prestacionDeServicio = this.obtenerPrestacion(context, entityManager);
        incidente.setPrestacionDeServicio(prestacionDeServicio);

        incidente.setEstado(false);
        incidente.setFechaHoraApertura(LocalDateTime.now());

        Usuario usuario = super.usuarioLogueado(context, entityManager);
        incidente.setUsuarioApertura(usuario);
    }

    private PrestacionDeServicio obtenerPrestacion(Context context, EntityManager entityManager) {
        EntityTransaction tx = entityManager.getTransaction();
        PrestacionDeServicio prestacionDeServicio;
        try {
            tx.begin();
            prestacionDeServicio = this.repositorioPrestaciones.obtenerPrestacion(
                    Long.parseLong(Objects.requireNonNull(context.formParam("entidad"))),
                    Long.parseLong(Objects.requireNonNull(context.formParam("establecimiento"))),
                    Long.parseLong(Objects.requireNonNull(context.formParam("servicio"))),
                    entityManager);
            tx.commit();
        } catch(NoResultException e) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw new InvalidPrestacionException();
        }

        return prestacionDeServicio;
    }

}