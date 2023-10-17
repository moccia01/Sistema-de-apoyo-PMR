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
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.*;

public class MiembroController extends Controller{
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
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id));
        this.repositorioMiembros.eliminar(miembro);
        context.redirect(Server.baseUrl + "/miembros/"
                + miembro.getComunidad().getId().toString() + "/admin");
    }

    public void editar(Context context) {
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
        String id = context.pathParam("miembro_id");
        Miembro miembro = this.repositorioMiembros.obtenerMiembro(Long.parseLong(id));
        Usuario usuario = this.repositorioUsuarios.obtenerUsuarioSegun(miembro.getUsuario().getId());
        this.asignarParametros(miembro, usuario, context);
        this.repositorioMiembros.modificar(miembro);
        context.redirect(miembro.getComunidad().getId() + "/admin");
    }

    public void create(Context context) {
        String id = context.pathParam("comunidad_id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(id));
        Map<String, Object> model = new HashMap<>();
        List<TiempoConfigurado> tiemposConfigurados = new ArrayList<>();
        tiemposConfigurados.add(new RepositorioTiemposConfiguracion().obtenerConfigCuandoSucede());
        model.put("tiempos_config", tiemposConfigurados);
        model.put("comunidad", comunidad);
        context.render("comunidades/miembro.hbs", model);
    }

    public void save(Context context) {
        Miembro miembro = new Miembro();
        Usuario usuario = new Usuario();
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso();
        usuario.setCredencialDeAcceso(credencialDeAcceso);
        miembro.setUsuario(usuario);

        this.asignarParametros(miembro, usuario, context);
        String id = context.queryParam("comunidad_id");
        Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(Long.parseLong(Objects.requireNonNull(id)));

        usuario.agregarMiembros(miembro);
        miembro.setComunidad(comunidad);

        comunidad.agregarMiembros(miembro);

        this.repositorioUsuarios.agregar(usuario);
        this.repositorioMiembros.agregar(miembro);
        this.repositorioComunidades.modificar(comunidad);

        context.redirect("miembros/" + comunidad.getId() + "/admin");
    }

    public void asignarParametros(Miembro miembro, Usuario usuario, Context contexto) {


        //Valores del form
        usuario.setNombre(contexto.formParam("nombre"));
        usuario.setApellido(contexto.formParam("apellido"));
        usuario.setMail(contexto.formParam("email"));
        usuario.setTelefono(contexto.formParam("telefono"));

        CredencialDeAcceso credencialDeAcceso = usuario.getCredencialDeAcceso();
        if(repositorioCredenciales.obtenerCredencialDe(contexto.formParam("usuario_nombre")) != null) {
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

        usuario.setMedioConfigurado(new MedioConfiguradoAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("medio_notificacion"))));
        String tiempoElegido = contexto.formParam("tiempo_configuracion");

        miembro.setRol(new RolAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("rol"))));

        miembro.setRolTemporal(new RolTemporalAttributeConverter().convertToEntityAttribute(
                Objects.requireNonNull(contexto.formParam("rol_temporal"))));

        TiempoConfigurado tiempoConfigurado = null;
        if(Objects.equals(tiempoElegido, "CuandoSucede")){
            tiempoConfigurado = repositorioTiemposConfiguracion.obtenerConfigCuandoSucede();
        } else {
            assert tiempoElegido != null;
            tiempoConfigurado = new TiempoConfiguradoAttributeConverter().convertToEntityAttribute(tiempoElegido);
        }
        usuario.setTiempoConfigurado(tiempoConfigurado);

        //Valores default
        usuario.setGradoDeConfianza(new RepositorioGradosDeConfianza().obtenerGradoDeConfianza(
                NombreGradoConfianza.CONFIABLE_NIVEL_1));
        usuario.setInteres(new Interes());
        usuario.setPuntosDeConfianza(5);

        usuario.setCredencialDeAcceso(credencialDeAcceso);
        miembro.setUsuario(usuario);
        this.repositorioUsuarios.modificar(usuario);
    }
}
