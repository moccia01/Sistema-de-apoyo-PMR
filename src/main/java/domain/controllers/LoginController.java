package domain.controllers;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Interes;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.repositorios.RepositorioCredenciales;
import domain.models.repositorios.RepositorioUsuarios;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginController {
    RepositorioUsuarios repositorioUsuarios;
    RepositorioCredenciales repositorioCredenciales;

    public LoginController(RepositorioUsuarios repositorioUsuarios, RepositorioCredenciales repositorioCredenciales) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioCredenciales = repositorioCredenciales;
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
        Map<String, Object> model = new HashMap<>();
        model.put("login", null);
        context.render("login/login.hbs", model);
    }

    public void login(Context context){
        String username = context.formParam("username");
     //   System.out.println("u: " + username);
        String password = context.formParam("password");
      //  System.out.println("pw: " + password);
        CredencialDeAcceso credencialDeAcceso = repositorioCredenciales.obtenerCredencial(username, password);
        if(credencialDeAcceso != null){
            Usuario usuario = repositorioUsuarios.obtenerUsuario(credencialDeAcceso);
            context.sessionAttribute("usuario_id", usuario.getId());
            context.redirect("/index");
        } else {
            context.render("/login/loginError.hbs");
        }

    }

    public void create(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("registro", null);
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

        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso();
        credencialDeAcceso.setNombreUsuario(contexto.formParam("usuario_nombre"));
        credencialDeAcceso.setContrasenia(contexto.formParam("contrasenia"));
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        usuario.setCredencialDeAcceso(credencialDeAcceso);

        usuario.setMail(contexto.formParam("email"));

        usuario.setMedioConfigurado(new MensajeWhatsApp());
        usuario.setTiempoConfigurado(new CuandoSucede());

        //Valores default
        usuario.setInteres(new Interes());
        usuario.setPuntosDeConfianza(5);
        GradoDeConfianza gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);
        usuario.setGradoDeConfianza(gradoDeConfianza);

    }
}
