package domain.controllers;

import domain.models.entities.comunidad.*;
import domain.models.entities.converters.MedioConfiguradoAttributeConverter;
import domain.models.entities.converters.RolAttributeConverter;
import domain.models.entities.converters.RolTemporalAttributeConverter;
import domain.models.entities.converters.TiempoConfiguradoAttributeConverter;
import domain.models.entities.mensajes.Configuraciones.TiempoConfigurado;
import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.EsDebil;
import domain.models.entities.validaciones.UsaCredencialesPorDefecto;
import domain.models.entities.validaciones.Validador;
import domain.models.entities.validaciones.politicasNIST.*;
import domain.models.repositorios.*;
import domain.server.Server;
import domain.server.exceptions.DuplicateUserException;
import domain.server.exceptions.InvalidPasswordException;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.*;

public class MiembroController extends Controller {
    private RepositorioMiembros repositorioMiembros;
    private RepositorioTiemposConfiguracion repositorioTiemposConfiguracion;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioCredenciales repositorioCredenciales;

    public MiembroController(RepositorioMiembros repositorioMiembros,
                             RepositorioTiemposConfiguracion repositorioTiemposConfiguracion,
                             RepositorioComunidades repositorioComunidades,
                             RepositorioUsuarios repositorioUsuarios, RepositorioCredenciales repositorioCredenciales) {
        this.repositorioMiembros = repositorioMiembros;
        this.repositorioTiemposConfiguracion = repositorioTiemposConfiguracion;
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioCredenciales = repositorioCredenciales;
    }

    public void baja(Context context) {
        String id = context.pathParam("miembro_id");
        EntityManager entityManager = Server.entityManager();
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id), entityManager);
        Comunidad comunidad = miembro.getComunidad();
        comunidad.eliminarMiembro(miembro);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            this.repositorioComunidades.modificar(comunidad, entityManager);
            this.repositorioMiembros.eliminar(miembro, entityManager);
        tx.commit();
        context.redirect("/miembros/" + miembro.getComunidad().getId().toString() + "/admin");
    }

    public void editar(Context context) {
        String id = context.pathParam("miembro_id");
        EntityManager entityManager = Server.entityManager();
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id), entityManager);
        Map<String, Object> model = new HashMap<>();
        List<TiempoConfigurado> tiemposConfigurados = new ArrayList<>();
        tiemposConfigurados.add(new RepositorioTiemposConfiguracion().obtenerConfigCuandoSucede(entityManager));
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("tiempos_config", tiemposConfigurados);
        model.put("miembro", miembro);
        context.render("usuarios/comunidades/miembro.hbs", model);
    }

    public void update(Context context) {
        EntityManager entityManager = Server.entityManager();
        String id = context.pathParam("miembro_id");
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id), entityManager);
        Usuario usuario = this.repositorioUsuarios.obtenerUsuarioSegun(miembro.getUsuario().getId(), entityManager);

        this.asignarParametros(miembro, usuario, context, entityManager, false);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            this.repositorioUsuarios.modificar(usuario, entityManager);
        tx.commit();
        context.redirect(miembro.getComunidad().getId() + "/admin");
    }

    public void create(Context context) {
        String id = context.pathParam("comunidad_id");
        EntityManager entityManager = Server.entityManager();
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(id), entityManager);
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = super.usuarioLogueado(context, entityManager);
        model.put("nombre", usuario.getNombre());
        model.put("apellido", usuario.getApellido());
        model.put("nombre_form", context.queryParam("nombre"));
        model.put("apellido_form", context.queryParam("apellido"));
        model.put("usuario_form", context.queryParam("usuario"));
        model.put("comunidad", comunidad);
        context.render("usuarios/comunidades/miembro.hbs", model);
    }

    public void save(Context context) {
        Miembro miembro = new Miembro();
        Usuario usuario = new Usuario();
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso();
        usuario.setCredencialDeAcceso(credencialDeAcceso);
        miembro.setUsuario(usuario);

        String id = context.pathParam("comunidad_id");
        EntityManager entityManager = Server.entityManager();
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(Objects.requireNonNull(id)), entityManager);
        context.sessionAttribute("error_return", "/miembros/" + comunidad.getId() + "/alta");
        this.asignarParametros(miembro, usuario, context, entityManager, true);

        usuario.agregarMiembros(miembro);
        miembro.setComunidad(comunidad);

        comunidad.agregarMiembros(miembro);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
            this.repositorioUsuarios.agregar(usuario, entityManager);
            this.repositorioComunidades.modificar(comunidad, entityManager);
        tx.commit();

        context.redirect("/miembros/" + comunidad.getId() + "/admin");
    }

    public void asignarParametros(Miembro miembro, Usuario usuario, Context contexto, EntityManager entityManager, Boolean alta) {

        //Valores del form
        usuario.setNombre(contexto.formParam("nombre"));
        usuario.setApellido(contexto.formParam("apellido"));
/*        usuario.setMail(contexto.formParam("email"));
        usuario.setTelefono(contexto.formParam("telefono"));*/

        CredencialDeAcceso credencialDeAcceso = usuario.getCredencialDeAcceso();

        if(alta && repositorioCredenciales.obtenerCredencialDe(contexto.formParam("usuario_nombre"), entityManager) != null) {
            throw new DuplicateUserException();
        }

        credencialDeAcceso.setNombreUsuario(contexto.formParam("usuario_nombre"));
        credencialDeAcceso.setContrasenia(contexto.formParam("contrasenia"));
        credencialDeAcceso.setFechaUltimoCambio(LocalDate.now());

        Validador validador = new Validador();
        validador.setValidaciones(new EsDebil(),new UsaCredencialesPorDefecto(),new Longitud(),new Rotacion(),new TieneCaracterEspecial(),new TieneMayuscula(),new TieneNumero());
        if(!validador.validar(credencialDeAcceso)) {
            throw new InvalidPasswordException();
        }

       /* usuario.setMedioConfigurado(new MedioConfiguradoAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("medio_notificacion"))));
        String tiempoElegido = contexto.formParam("tiempo_configuracion");*/

        miembro.setRol(new RolAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("rol"))));

        miembro.setRolTemporal(new RolTemporalAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("rol_temporal"))));
        /*
        TiempoConfigurado tiempoConfigurado = null;
        if(Objects.equals(tiempoElegido, "CuandoSucede")){
            tiempoConfigurado = repositorioTiemposConfiguracion.obtenerConfigCuandoSucede();
        } else {
            assert tiempoElegido != null;
            tiempoConfigurado = new TiempoConfiguradoAttributeConverter().convertToEntityAttribute(tiempoElegido);
        }
        usuario.setTiempoConfigurado(tiempoConfigurado);*/

        //Valores default
        usuario.setGradoDeConfianza(new RepositorioGradosDeConfianza().obtenerGradoDeConfianza(entityManager,
                NombreGradoConfianza.CONFIABLE_NIVEL_1));
        usuario.setInteres(new Interes());
        usuario.setPuntosDeConfianza(5);

        usuario.setCredencialDeAcceso(credencialDeAcceso);
        miembro.setUsuario(usuario);
    }
}
