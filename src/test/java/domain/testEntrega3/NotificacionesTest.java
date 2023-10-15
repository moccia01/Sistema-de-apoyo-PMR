package domain.testEntrega3;

import domain.models.entities.mensajes.Configuraciones.CuandoSucede;
import domain.models.entities.mensajes.Configuraciones.MensajeEmail;
import domain.models.entities.mensajes.Configuraciones.MensajeWhatsApp;
import domain.models.entities.mensajes.Configuraciones.SinApuros;
import domain.models.entities.mensajes.MailSender;
import domain.models.entities.mensajes.NotificacionesPendientesSender;
import domain.models.entities.mensajes.WhatsAppSender;
import domain.models.entities.builders.EstablecimientoBuilder;
import domain.models.entities.builders.InteresBuilder;
import domain.models.entities.builders.PrestacionDeServicioBuilder;
import domain.models.entities.entidadesDeServicio.Entidad;
import domain.models.entities.entidadesDeServicio.Establecimiento;
import domain.models.entities.entidadesDeServicio.PrestacionDeServicio;
import domain.models.entities.entidadesDeServicio.Servicio;
import domain.models.entities.comunidad.*;
import domain.models.repositorios.RepositorioUsuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificacionesTest {

    private Miembro miembro;
    private Miembro elFede;
    private Miembro elTomas;
    private Usuario usuario;
    private Usuario fede;
    private Usuario tomas;
    private Comunidad comunidad;
    private Comunidad operativosEnjoyers;
    private Interes interes;
    private Entidad utn;
    private Establecimiento medrano;
    private Establecimiento campus;
    private Servicio escalera;
    private Servicio banio;
    private PrestacionDeServicio escaleraMedrano;
    private PrestacionDeServicio banioCampus;
    private RepositorioUsuarios repositorioUsuarios;
    private List<Usuario> usuarios = new ArrayList<>();

    @BeforeEach
    public void init() {
        usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");
        miembro = new Miembro(usuario, Rol.MIEMBRO, comunidad);
        usuario.agregarMiembros(miembro);

        fede = new Usuario();
        fede.setMail("federico21433@hotmail.com");
        elFede = new Miembro(fede, Rol.ADMINISTRADOR, operativosEnjoyers);
        fede.agregarMiembros(elFede);

        tomas = new Usuario();
        tomas.setMail("tomydanto84@gmail.com");
        elTomas = new Miembro(tomas, Rol.ADMINISTRADOR, operativosEnjoyers);
        tomas.agregarMiembros(elTomas);

        comunidad = new Comunidad();
        operativosEnjoyers = new Comunidad();

        utn = new Entidad();
        utn.setNombre("UTN");

        escalera = new Servicio();
        escalera.setNombre("Escalera");

        banio = new Servicio();
        banio.setNombre("Baño");

        EstablecimientoBuilder establecimientoBuilder = new EstablecimientoBuilder();
        medrano = establecimientoBuilder.conNombre("Medrano")
                .conServicios(escalera)
                .conLocalizacion("Buenos Aires", "Comuna 5", "Medrano 951")
                .construir();

        EstablecimientoBuilder establecimientoBuilder1 = new EstablecimientoBuilder();
        campus = establecimientoBuilder1.conNombre("Campus")
                .conServicios(escalera, banio)
                .conLocalizacion("Buenos Aires", "Comuna 8", "Mozart 2300")
                .construir();

        PrestacionDeServicioBuilder prestacionDeServicioBuilder = new PrestacionDeServicioBuilder();
        escaleraMedrano = prestacionDeServicioBuilder.conEntidad(utn)
                .conEstablecimiento(medrano).conServicio(escalera).construir();

        PrestacionDeServicioBuilder prestacionDeServicioBuilder1 = new PrestacionDeServicioBuilder();
        banioCampus = prestacionDeServicioBuilder1.conEntidad(utn)
                .conEstablecimiento(campus).conServicio(banio).construir();

        InteresBuilder interesBuilder = new InteresBuilder();
        interes = interesBuilder.agregarEntidades(utn).agregarServicios(escalera, banio).construir();

        comunidad.agregarMiembros(miembro);
        usuario.setInteres(interes);

        operativosEnjoyers.agregarMiembros(elFede, elTomas);
        fede.setInteres(interes);
        tomas.setInteres(interes);

        usuarios.add(fede);
        usuarios.add(tomas);
        repositorioUsuarios = Mockito.mock(RepositorioUsuarios.class);
        when(repositorioUsuarios.obtenerUsuarios()).thenReturn(usuarios);

    }

    @Test
    public void seEnviaUnMailCuandoHayGeneracionDeIncidente() {
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);
        usuario.setMedioConfigurado(medio);
        usuario.setTiempoConfigurado(new CuandoSucede());

        comunidad.generarIncidente(new Usuario(), "titulo", escaleraMedrano, "Se rompió la baranda");

        Mockito.verify(mailer, Mockito.only()).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void seEnviaUnWhatsAppCuandoHayGeneracionDeIncidente() {
        WhatsAppSender whatsapper = Mockito.mock(WhatsAppSender.class);
        MensajeWhatsApp medio = new MensajeWhatsApp(whatsapper);
        usuario.setMedioConfigurado(medio);
        usuario.setTiempoConfigurado(new CuandoSucede());

        comunidad.generarIncidente(new Usuario(), "titulo",escaleraMedrano, "Se rompió la baranda");

        Mockito.verify(whatsapper, Mockito.only()).enviarMensaje(Mockito.any(), Mockito.any());
    }

    @Test
    public void seNotificaCuandoHayCierreDeIncidente(){
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail enviarMail = new MensajeEmail(mailer);
        CuandoSucede cuandoSucede = new CuandoSucede();

        usuario.setTiempoConfigurado(cuandoSucede);
        usuario.setMedioConfigurado(enviarMail);

        comunidad.generarIncidente(new Usuario(), "titulo",banioCampus, "Estan arreglando el baño del primer piso");

        usuario.cerrarIncidente(comunidad, comunidad.getIncidentes().get(0));
        Assertions.assertTrue(comunidad.getIncidentes().get(0).getEstado());
    }

    @Test
    public void seMandanNotificacionesPendientesConConfigSinApuros(){
        MailSender mailer = Mockito.mock(MailSender.class);
        MailSender mailerMiembro = Mockito.mock(MailSender.class);
        MensajeEmail email = new MensajeEmail(mailer);
        MensajeEmail emailMiembro = new MensajeEmail(mailerMiembro);
        SinApuros sinApurosTomas = Mockito.mock(SinApuros.class);
        SinApuros sinApurosFede = Mockito.mock(SinApuros.class);
        CuandoSucede cuandoSucede = new CuandoSucede();

        when(sinApurosTomas.esHoradeMandarPendientes()).thenReturn(true);
        when(sinApurosFede.esHoradeMandarPendientes()).thenReturn(true);
        Mockito.doCallRealMethod().when(sinApurosFede).mandarPendientes(Mockito.any());
        Mockito.doCallRealMethod().when(sinApurosTomas).mandarPendientes(Mockito.any());
        Mockito.doCallRealMethod().when(sinApurosFede).inicializarNotificacionesPendientes();
        Mockito.doCallRealMethod().when(sinApurosTomas).inicializarNotificacionesPendientes();
        Mockito.doCallRealMethod().when(sinApurosFede).recibirNotificacion(Mockito.any(), Mockito.any());
        Mockito.doCallRealMethod().when(sinApurosTomas).recibirNotificacion(Mockito.any(), Mockito.any());

        sinApurosFede.inicializarNotificacionesPendientes();
        sinApurosTomas.inicializarNotificacionesPendientes();

        usuario.setTiempoConfigurado(cuandoSucede);
        usuario.setMedioConfigurado(email);
        tomas.setTiempoConfigurado(sinApurosTomas);
        tomas.setMedioConfigurado(email);
        fede.setTiempoConfigurado(sinApurosFede);
        fede.setMedioConfigurado(email);

        operativosEnjoyers.generarIncidente(new Usuario(), "titulo",escaleraMedrano, "se rompio un escalon, me cai");
        operativosEnjoyers.generarIncidente(new Usuario(), "titulo",banioCampus, "no tira la cadena del inodoro 2, está tapado");

        Incidente incidenteBanioCampus = operativosEnjoyers.getIncidentes().get(1);
        operativosEnjoyers.cerrarIncidente(incidenteBanioCampus);

        Mockito.verify(sinApurosTomas, Mockito.never()).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.never()).mandarPendientes(Mockito.any());

        NotificacionesPendientesSender.mandarPendientes(repositorioUsuarios.obtenerUsuarios());

        Mockito.verify(sinApurosTomas, Mockito.times(1)).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.times(1)).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.times(1)).esHoradeMandarPendientes();
        Mockito.verify(mailer, Mockito.times(6)).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(mailerMiembro, Mockito.never()).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
