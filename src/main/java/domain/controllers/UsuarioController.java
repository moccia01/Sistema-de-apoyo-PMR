package domain.controllers;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.MedioConfiguradoAttributeConverter;
import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.repositorios.RepositorioTiemposConfiguracion;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsuarioController extends Controller implements WithSimplePersistenceUnit {
    private RepositorioTiemposConfiguracion repositorioTiemposConfiguracion;

    public UsuarioController(RepositorioTiemposConfiguracion repositorioTiemposConfiguracion) {
        this.repositorioTiemposConfiguracion = repositorioTiemposConfiguracion;
    }

    public void index(Context context) {
        Map<String, Object> model = this.cargarUsuarioEnModel(context);
        context.render("usuarios/index.hbs", model);
    }

    public void perfil(Context context) {
        Map<String, Object> model = this.cargarUsuarioEnModel(context);
        context.render("login/registro.hbs", model);
    }

    public void update(Context context) {
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        this.asignarParametros(usuario, context, entityManager);

        beginTransaction();
        entityManager.merge(usuario);
        commitTransaction();

        context.redirect("index");
    }

    private Map<String, Object> cargarUsuarioEnModel(Context context) {
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("user", usuario);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        return model;
    }

    public void asignarParametros(Usuario usuario, Context context, EntityManager entityManager){
        String nombreSeteado = context.formParam("nombre");
        String apellidoSeteado = context.formParam("apellido");
        String usuario_nombreSeteado = context.formParam("usuario_nombre");
        String emailSeteado = context.formParam("email");
        String telefonoSeteado = context.formParam("telefono");
        String contraseniaSeteada = context.formParam("contrasenia");

        if(!Objects.equals(usuario.getNombre(), nombreSeteado)){
            usuario.setNombre(nombreSeteado);
        }
        if(!Objects.equals(usuario.getApellido(), apellidoSeteado)){
            usuario.setApellido(apellidoSeteado);
        }
        if(!Objects.equals(usuario.getCredencialDeAcceso().getNombreUsuario(), usuario_nombreSeteado)){
            usuario.getCredencialDeAcceso().setNombreUsuario(usuario_nombreSeteado);
        }
        if(!Objects.equals(usuario.getMail(), emailSeteado)){
            usuario.setMail(emailSeteado);
        }
        if(!Objects.equals(usuario.getTelefono(), telefonoSeteado)){
            usuario.setTelefono(telefonoSeteado);
        }
        if(!Objects.equals(usuario.getCredencialDeAcceso().getContrasenia(), contraseniaSeteada)){
            usuario.getCredencialDeAcceso().setContrasenia(contraseniaSeteada);
        }

        String medioSeteado = context.formParam("medio_notificacion");
        if(!Objects.equals(medioSeteado, "-1")){
            usuario.setMedioConfigurado(new MedioConfiguradoAttributeConverter()
                    .convertToEntityAttribute(medioSeteado));
        }

        String tiempoConfigurado = context.formParam("tiempo_configuracion");
        if(!Objects.equals(tiempoConfigurado, "-1")){
            switch (Objects.requireNonNull(tiempoConfigurado)) {
                case "CuandoSucede" -> usuario.setTiempoConfigurado(
                        this.repositorioTiemposConfiguracion.obtenerConfigCuandoSucede(entityManager));
                case "SinApuros" -> usuario.setTiempoConfigurado(new TiempoConfiguradoAttributeConverter()
                        .convertToEntityAttribute(tiempoConfigurado));
            }
        }

    }

    public void quienesSomos(Context context){
        Map<String, Object> model = new HashMap<>();
        EntityManager entityManager = entityManager();
        Usuario usuario = null;
        if(context.sessionAttribute("usuario_id") != null) {
            usuario = entityManager.find(Usuario.class, Objects.requireNonNull(context.sessionAttribute("usuario_id")));
            model.put("nombre", usuario.getNombre());
            model.put("apellido", usuario.getApellido());
            model.put("usuario", usuario.getCredencialDeAcceso().getNombreUsuario());
        }
        model.put("user", usuario);
        context.render("usuarios/quienesSomos.hbs", model);
    }
}
