package domain.controllers;

import domain.models.entities.comunidad.*;
import domain.models.repositorios.RepositorioComunidades;
import domain.models.repositorios.RepositorioMiembros;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.exceptions.AccessDeniedException;
import io.javalin.http.Context;

import java.util.*;

public class ComunidadController extends Controller{
    private RepositorioComunidades repositorioComunidades;
    private RepositorioMiembros repositorioMiembros;
    private RepositorioUsuarios repositorioUsuarios;

    public ComunidadController(RepositorioComunidades repositorioComunidades, RepositorioUsuarios repositorioUsuarios, RepositorioMiembros repositorioMiembros){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioMiembros = repositorioMiembros;
    }
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Miembro> miembros = this.repositorioMiembros.obtenerMiembrosDe(super.usuarioLogueado(context).getId());
        model.put("miembros", miembros);
        context.render("comunidades/comunidades.hbs", model);
    }

    public void admin(Context context) {
        String comunidad_id = context.pathParam("id");
        Usuario usuario = super.usuarioLogueado(context);
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(comunidad_id));
        Miembro miembro = this.repositorioMiembros.obtenerMiembroDe(usuario.getId(), comunidad.getId());

        if(!miembro.esAdministrador()) {
            throw new AccessDeniedException();
        }
        List<Miembro> miembros = this.repositorioComunidades.obtenerMiembrosDe(comunidad.getId());
        Map<String, Object> model = new HashMap<>();
        model.put("miembro", miembro);
        model.put("miembros", miembros);
        model.put("comunidad", comunidad);
        context.render("comunidades/admin.hbs", model);
    }

    public void ver(Context context) {
        String comunidad_id = context.pathParam("id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(comunidad_id));
        List<Miembro> miembros = this.repositorioComunidades.obtenerMiembrosDe(comunidad.getId());
        Map<String, Object> model = new HashMap<>();
        model.put("miembros", miembros);
        model.put("comunidad", comunidad);
        context.render("comunidades/ver.hbs", model);
    }



    private void asignarParametros(Comunidad comunidad, Context contexto){

    }

}
