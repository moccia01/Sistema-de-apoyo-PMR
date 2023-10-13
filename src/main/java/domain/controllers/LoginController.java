package domain.controllers;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Interes;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.converters.MedioConfiguradoAttributeConverter;
import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.RepositorioCredenciales;
import domain.models.repositorios.RepositorioGradosDeConfianza;
import domain.models.repositorios.RepositorioTiemposConfiguracion;
import domain.models.repositorios.RepositorioUsuarios;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.*;

public class LoginController {
    RepositorioUsuarios repositorioUsuarios;
    RepositorioCredenciales repositorioCredenciales;
    RepositorioTiemposConfiguracion repositorioTiemposConfiguracion;
    RepositorioGradosDeConfianza repositorioGradosDeConfianza;

    public LoginController(RepositorioUsuarios repositorioUsuarios,
                           RepositorioCredenciales repositorioCredenciales,
                           RepositorioTiemposConfiguracion repositorioTiemposConfiguracion,
                           RepositorioGradosDeConfianza repositorioGradosDeConfianza) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioCredenciales = repositorioCredenciales;
        this.repositorioTiemposConfiguracion = repositorioTiemposConfiguracion;
        this.repositorioGradosDeConfianza= repositorioGradosDeConfianza;
    }

    public void index(Context context){
        // MUESTRA LA VISTA QUE TIENE OPCIONES DE INICIO SESION O REGISTRO
        Map<String, Object> model = new HashMap<>();
        model.put("login", null);
        if(context.sessionAttribute("usuario_id") != null) {
            context.redirect("/index");
        } else {
            context.render("login/main.hbs", model);
        }
    }

    public void show(Context context){
        context.sessionAttribute("usuario_id", null);
        Map<String, Object> model = new HashMap<>();
        model.put("login", null);
        context.render("login/login.hbs", model);
    }

    public void login(Context context){
        String username = context.formParam("username");
        String password = context.formParam("password");
        CredencialDeAcceso credencialDeAcceso = repositorioCredenciales.obtenerCredencial(username, password);
        if(credencialDeAcceso != null){
            Usuario usuario = repositorioUsuarios.obtenerUsuario(credencialDeAcceso);
            context.sessionAttribute("usuario_id", usuario.getId());
            context.redirect("/index");
        } else {
            context.render("errors/loginError.hbs");
        }

    }

    public void create(Context context){
        Map<String, Object> model = new HashMap<>();
        List<TiempoConfigurado> tiemposConfigurados= new ArrayList<>();
        tiemposConfigurados.add(new RepositorioTiemposConfiguracion().obtenerTiempoConfigurado("CuandoSucede"));
        model.put("tiempos_config", tiemposConfigurados);
        context.render("login/registro.hbs", model);
    }

    public void save(Context context){
        Usuario usuario = new Usuario();
        //TODO podriamos usar el validador para ver si la contraseña es valida
        this.asignarParametros(usuario, context);
        this.repositorioUsuarios.agregar(usuario);
        context.redirect("/login");
    }

    public void asignarParametros(Usuario usuario, Context contexto) {
        //TODO completar cuando este la vista de registro

        //Valores del form
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
            tiempoConfigurado = repositorioTiemposConfiguracion.obtenerTiempoConfigurado(tiempoElegido);
        } else {
            assert tiempoElegido != null;
            tiempoConfigurado = new TiempoConfiguradoAttributeConverter().convertToEntityAttribute(tiempoElegido);
        }
        usuario.setTiempoConfigurado(tiempoConfigurado);

        usuario.setGradoDeConfianza(repositorioGradosDeConfianza.obtenerGradoDeConfianza(
                NombreGradoConfianza.CONFIABLE_NIVEL_1));

        //Valores default
        usuario.setInteres(new Interes());
        usuario.setPuntosDeConfianza(5);
    }
}
