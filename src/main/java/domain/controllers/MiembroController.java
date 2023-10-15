package domain.controllers;

import domain.models.entities.comunidad.Miembro;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.MedioConfiguradoAttributeConverter;
import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.RepositorioMiembros;
import domain.models.repositorios.RepositorioTiemposConfiguracion;
import domain.server.Server;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.*;

public class MiembroController extends Controller{
    private RepositorioMiembros repositorioMiembros;
    private RepositorioTiemposConfiguracion repositorioTiemposConfiguracion;

    public MiembroController(RepositorioMiembros repositorioMiembros,
                             RepositorioTiemposConfiguracion repositorioTiemposConfiguracion) {
        this.repositorioMiembros = repositorioMiembros;
        this.repositorioTiemposConfiguracion = repositorioTiemposConfiguracion;
    }

    public void baja(Context context) {
        // TODO directamente tirar la baja a base de datos
        String id = context.pathParam("miembro_id");
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id));
        this.repositorioMiembros.eliminar(miembro);
        context.redirect(Server.baseUrl + "comunidades/"
                + miembro.getComunidad().getId().toString() + "/admin");
    }

    public void editar(Context context) {
        //TODO devolver la misma vista que registro de usuario
        String id = context.pathParam("miembro_id");
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id));
        Map<String, Object> model = new HashMap<>();
        List<TiempoConfigurado> tiemposConfigurados = new ArrayList<>();
        tiemposConfigurados.add(new RepositorioTiemposConfiguracion().obtenerConfigCuandoSucede());
        model.put("tiempos_config", tiemposConfigurados);
        model.put("miembro", miembro);
        context.render("comunidades/miembro.hbs", model);
    }

    public void update(Context context) {
        // TODO tirar el update a base de datos
        String id = context.pathParam("miembro_id");
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id));
        this.asignarParametros(miembro, context);
        this.repositorioMiembros.modificar(miembro);

        //TODO no funca el redirect ?
        context.redirect(Server.baseUrl + "comunidades/"
                + miembro.getComunidad().getId().toString() + "/admin");
    }

    public void asignarParametros(Miembro miembro, Context contexto) {
        //Valores del form
        Usuario usuario = miembro.getUsuario();
        usuario.setNombre(contexto.formParam("nombre"));
        usuario.setApellido(contexto.formParam("apellido"));
        usuario.setMail(contexto.formParam("email"));
        usuario.setTelefono(contexto.formParam("telefono"));

        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso();
        credencialDeAcceso.setNombreUsuario(contexto.formParam("usuario_nombre"));
        credencialDeAcceso.setContrasenia(contexto.formParam("contrasenia"));
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        usuario.setCredencialDeAcceso(credencialDeAcceso);

        usuario.setMedioConfigurado(new MedioConfiguradoAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("medio_notificacion"))));
        String tiempoElegido = contexto.formParam("tiempo_configuracion");

        TiempoConfigurado tiempoConfigurado = null;
        if(Objects.equals(tiempoElegido, "CuandoSucede")){
            tiempoConfigurado = repositorioTiemposConfiguracion.obtenerConfigCuandoSucede();
        } else {
            assert tiempoElegido != null;
            tiempoConfigurado = new TiempoConfiguradoAttributeConverter().convertToEntityAttribute(tiempoElegido);
        }
        usuario.setTiempoConfigurado(tiempoConfigurado);
    }
}
