package domain.controllers;

import domain.models.entities.comunidad.GradoDeConfianza;
import domain.models.entities.comunidad.Interes;
import domain.models.entities.comunidad.NombreGradoConfianza;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.EsDebil;
import domain.models.entities.validaciones.UsaCredencialesPorDefecto;
import domain.models.entities.validaciones.Validador;
import domain.models.entities.validaciones.politicasNIST.*;
import domain.models.repositorios.RepositorioCredenciales;
import domain.models.repositorios.RepositorioGradosDeConfianza;
import domain.models.repositorios.RepositorioTiemposConfiguracion;
import domain.models.repositorios.RepositorioUsuarios;
import domain.server.Server;
import domain.server.exceptions.DuplicateUserException;
import domain.server.exceptions.InvalidPasswordException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        context.sessionAttribute("admin_id", null);
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", true);
        model.put("login", null);
        context.render("login/login.hbs", model);
    }

    public void login(Context context){
        String username = context.formParam("username");
        String password = context.formParam("password");
        EntityManager entityManager = Server.entityManager();
        CredencialDeAcceso credencialDeAcceso = repositorioCredenciales.obtenerCredencial(username, password, entityManager);
        if(credencialDeAcceso != null){
            Usuario usuario = repositorioUsuarios.obtenerUsuario(credencialDeAcceso, entityManager);
            context.sessionAttribute("usuario_id", usuario.getId());
            context.redirect("/index");
        } else {
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", true);
            context.render("errors/loginError.hbs", model);
        }

    }

    public void create(Context context){
        Map<String, Object> model = new HashMap<>();
        model.put("nombre", context.queryParam("nombre"));
        model.put("apellido", context.queryParam("apellido"));
        model.put("usuario", context.queryParam("usuario"));
        context.render("login/registro.hbs", model);
    }

    public void save(Context context){
        Usuario usuario = new Usuario();
        context.sessionAttribute("error_return", "/registro");
        EntityManager entityManager = Server.entityManager();
        EntityTransaction tx = entityManager.getTransaction();
        this.asignarParametros(usuario, context, entityManager);
        tx.begin();
        this.repositorioUsuarios.agregar(usuario, entityManager);
        tx.commit();
        context.redirect("/login");
    }

    public void asignarParametros(Usuario usuario, Context contexto, EntityManager entityManager) {

        //Valores del form
        usuario.setNombre(contexto.formParam("nombre"));
        usuario.setApellido(contexto.formParam("apellido"));
/*        usuario.setMail(contexto.formParam("email"));
        usuario.setTelefono(contexto.formParam("telefono"));*/

        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso();
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        credencialDeAcceso.setNombreUsuario(contexto.formParam("usuario_nombre"));
        if(repositorioCredenciales.obtenerCredencialDe(credencialDeAcceso.getNombreUsuario(), entityManager) != null) {
            throw new DuplicateUserException();
        }

        credencialDeAcceso.setContrasenia(contexto.formParam("contrasenia"));
        Validador validador = new Validador();
        validador.setValidaciones(new EsDebil(),new UsaCredencialesPorDefecto(),new Longitud(),new Rotacion(),new TieneCaracterEspecial(),new TieneMayuscula(),new TieneNumero());
        if(!validador.validar(credencialDeAcceso)) {
             throw new InvalidPasswordException();
        }


        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());
        usuario.setCredencialDeAcceso(credencialDeAcceso);

        /*usuario.setMedioConfigurado(new MedioConfiguradoAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("medio_notificacion"))));
        String tiempoElegido = contexto.formParam("tiempo_configuracion");

        TiempoConfigurado tiempoConfigurado = null;
        if(Objects.equals(tiempoElegido, "CuandoSucede")){
            tiempoConfigurado = repositorioTiemposConfiguracion.obtenerConfigCuandoSucede();
        } else {
            assert tiempoElegido != null;
            tiempoConfigurado = new TiempoConfiguradoAttributeConverter().convertToEntityAttribute(tiempoElegido);
        }
        usuario.setTiempoConfigurado(tiempoConfigurado);*/


        //Valores default
        usuario.setGradoDeConfianza(repositorioGradosDeConfianza.obtenerGradoDeConfianza(entityManager,
                NombreGradoConfianza.CONFIABLE_NIVEL_1));
        usuario.setInteres(new Interes());
        usuario.setPuntosDeConfianza(5);
    }
}