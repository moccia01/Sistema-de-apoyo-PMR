package domain.controllers;

import domain.models.entities.comunidad.*;
import domain.models.repositorios.RepositorioComunidades;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;

public class ComunidadController extends Controller implements ICrudViewsHandler {
    private RepositorioComunidades repositorioComunidades;
    private RepositorioUsuarios repositorioUsuarios;

    public ComunidadController(RepositorioComunidades repositorioComunidades, RepositorioUsuarios repositorioUsuarios){
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioUsuarios = repositorioUsuarios;
    }
    public void index(Context context) {
        //TODO ver como hacer la paginacion
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidades = new ArrayList<>(); //= this.repositorioComunidades.obtenerComunidadesDe(usuario_id);
        model.put("comunidades", comunidades);
        context.render("comunidades/comunidades.hbs", model);
    }


    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", null);
        context.render("comunidades/comunidades.hbs", model);
    }

    @Override
    public void save(Context context) {
        Comunidad comunidad = new Comunidad();
        this.asignarParametros(comunidad, context);
        this.repositorioComunidades.agregar(comunidad);
        context.redirect("/comunidades");
    }

    @Override
    public void edit(Context context) {
        String id = context.pathParam("id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Integer.parseInt(id));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidad);

        int usuario_id = context.sessionAttribute("usuario_id");
        Usuario usuario = new Usuario();
        usuario = repositorioUsuarios.obtenerUsuarioSegunId(usuario_id);
        model.put("rol",usuario.getM);
        context.render("comunidades/comunidades.hbs", model);
    }

    @Override
    public void update(Context context) {
        String id = context.pathParam("id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Integer.parseInt(id));
        this.asignarParametros(comunidad, context);
        this.repositorioComunidades.modificar(comunidad);
        context.redirect("/comunidades");
    }

    @Override
    public void delete(Context context) {
        String id = context.pathParam("id");
        Comunidad comunidad = (Comunidad) this.repositorioComunidades.obtenerComunidad(Integer.parseInt(id));
        this.repositorioComunidades.eliminar(comunidad);
        context.redirect("/comunidades");
    }

    private void asignarParametros(Comunidad comunidad, Context contexto){
        // podria ser un factory/builder (?)
        if(!Objects.equals(contexto.formParam("titulo"), "")) {
            comunidad.setNombre(contexto.formParam("titulo"));
        }
        // TODO implementar asignaciones para el resto de los inputs
        // si recibimos un id tipo entidad_id hay que instanciar un repo de entidades y buscarlo por id en la bd
        // y asi con todos los inputs del form


    }

}
