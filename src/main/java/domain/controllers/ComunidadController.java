package domain.controllers;

import domain.models.entities.comunidad.*;
import domain.models.repositorios.RepositorioComunidades;
import domain.models.repositorios.RepositorioMiembros;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;
import domain.server.exceptions.AccessDeniedException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

import javax.persistence.EntityManager;
import java.util.*;

public class ComunidadController extends Controller implements WithSimplePersistenceUnit {
    private RepositorioComunidades repositorioComunidades;
    private RepositorioMiembros repositorioMiembros;

    public ComunidadController(RepositorioComunidades repositorioComunidades, RepositorioMiembros repositorioMiembros){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioMiembros = repositorioMiembros;
    }
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        List<Miembro> miembros = this.repositorioMiembros.obtenerMiembrosDe(super.usuarioLogueado(context, entityManager).getId(), entityManager);
        model.put("miembros", miembros);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/comunidades/comunidades.hbs", model);
    }

    public void admin(Context context) {
        String comunidad_id = context.pathParam("id");
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(comunidad_id), entityManager);
        Miembro miembro = this.repositorioMiembros.obtenerMiembroDe(usuario.getId(), comunidad.getId(), entityManager);

        if(!miembro.esAdministrador()) {
            throw new AccessDeniedException();
        }

        List<Miembro> miembros = this.repositorioComunidades.obtenerMiembrosDe(comunidad.getId(), entityManager);
        Map<String, Object> model = new HashMap<>();
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("miembro", miembro);
        model.put("miembros", miembros);
        model.put("comunidad", comunidad);
        context.render("usuarios/comunidades/admin.hbs", model);
    }

    public void ver(Context context) {
        String comunidad_id = context.pathParam("id");
        EntityManager entityManager = entityManager();
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(comunidad_id), entityManager);
        List<Miembro> miembros = this.repositorioComunidades.obtenerMiembrosDe(comunidad.getId(), entityManager);
        Map<String, Object> model = new HashMap<>();
        model.put("miembros", miembros);
        model.put("comunidad", comunidad);
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        context.render("usuarios/comunidades/ver.hbs", model);
    }

}
