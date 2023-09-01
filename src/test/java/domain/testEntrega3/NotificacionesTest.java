package domain.testEntrega3;

import domain.mensajes.Configuraciones.*;
import domain.mensajes.MailSender;
import domain.mensajes.NotificacionesPendientesSender;
import domain.mensajes.WhatsAppSender;
import domain.builders.EstablecimientoBuilder;
import domain.builders.InteresBuilder;
import domain.builders.PrestacionDeServicioBuilder;
import domain.comunidad.*;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import domain.rankings.RepositorioComunidades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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


    @BeforeEach
    public void init() {
        usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");
        miembro = new Miembro(usuario, Rol.MIEMBRO);

        fede = new Usuario();
        fede.setMail("federico21433@hotmail.com");
        elFede = new Miembro(fede, Rol.ADMINISTRADOR);

        tomas = new Usuario();
        tomas.setMail("tomydanto84@gmail.com");
        elTomas = new Miembro(tomas, Rol.ADMINISTRADOR);

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
                .conServicios(escalera)
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

        comunidad.agregarMiembro(miembro);
        miembro.agregarComunidad(comunidad);
        usuario.setInteres(interes);

        operativosEnjoyers.agregarMiembros(elFede, elTomas);
        elFede.agregarComunidad(operativosEnjoyers);
        elTomas.agregarComunidad(operativosEnjoyers);
        fede.setInteres(interes);
        tomas.setInteres(interes);

        RepositorioComunidades.agregarComunidades(operativosEnjoyers, comunidad);
        NotificacionesPendientesSender.agregarMiembros(RepositorioComunidades.obtenerMiembros());
    }

    @Test
    public void seEnviaUnMailCuandoHayGeneracionDeIncidente() {
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);
        miembro.setMedioConfigurado(medio);
        miembro.setTiempoConfigurado(new CuandoSucede());

        comunidad.generarIncidente(escaleraMedrano, "Se rompió la baranda");

        Mockito.verify(mailer, Mockito.only()).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void seEnviaUnWhatsAppCuandoHayGeneracionDeIncidente() {
        WhatsAppSender whatsapper = Mockito.mock(WhatsAppSender.class);
        MensajeWhatsApp medio = new MensajeWhatsApp(whatsapper);
        miembro.setMedioConfigurado(medio);
        miembro.setTiempoConfigurado(new CuandoSucede());

        comunidad.generarIncidente(escaleraMedrano, "Se rompió la baranda");

        Mockito.verify(whatsapper, Mockito.only()).enviarMensaje(Mockito.any(), Mockito.any());
    }

    @Test
    public void seNotificaCuandoHayCierreDeIncidente(){
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail enviarMail = new MensajeEmail(mailer);
        CuandoSucede cuandoSucede = new CuandoSucede();

        miembro.setTiempoConfigurado(cuandoSucede);
        miembro.setMedioConfigurado(enviarMail);

        comunidad.generarIncidente(banioCampus, "Estan arreglando el baño del primer piso");

        miembro.cerrarIncidente(comunidad, comunidad.getIncidentes().get(0));
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

        miembro.setTiempoConfigurado(cuandoSucede);
        miembro.setMedioConfigurado(email);
        elTomas.setTiempoConfigurado(sinApurosTomas);
        elTomas.setMedioConfigurado(email);
        elFede.setTiempoConfigurado(sinApurosFede);
        elFede.setMedioConfigurado(email);

        operativosEnjoyers.generarIncidente(escaleraMedrano, "se rompio un escalon, me cai");
        operativosEnjoyers.generarIncidente(banioCampus, "no tira la cadena del inodoro 2, está tapado");

        Incidente incidenteBanioCampus = operativosEnjoyers.getIncidentes().get(1);
        operativosEnjoyers.cerrarIncidente(incidenteBanioCampus);

        Mockito.verify(sinApurosTomas, Mockito.never()).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.never()).mandarPendientes(Mockito.any());

        NotificacionesPendientesSender.mandarPendientes();

        Mockito.verify(sinApurosTomas, Mockito.times(1)).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.times(1)).mandarPendientes(Mockito.any());
        Mockito.verify(sinApurosFede, Mockito.times(1)).esHoradeMandarPendientes();
        Mockito.verify(mailer, Mockito.times(6)).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(mailerMiembro, Mockito.never()).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
    }
}

/*
    @Test
    public void seEnviaNotificacionSugerenciaCuandoMiembroEstaCercaDeIncidente(){
        Establecimiento medrano = new Establecimiento();
        medrano.setLocalizacion("Buenos Aires", "Comuna 5", "Medrano 951");
        PrestacionDeServicio ascensorMedrano = new PrestacionDeServicio();
        ascensorMedrano.setEstablecimiento(medrano);

        Establecimiento campus = new Establecimiento();
        campus.setLocalizacion("Buenos Aires", "comuna 8", "Mozart 2300");
        PrestacionDeServicio banioCampus = new PrestacionDeServicio();
        banioCampus.setEstablecimiento(campus);

        MensajeEmail enviarMail = new MensajeEmail();
        CuandoSucede cuandoSucede = new CuandoSucede();

        Usuario usuario = new Usuario();
        usuario.setLocalizacion("Buenos Aires","Comuna 5","Medrano 800");
        usuario.setMail("federico21433@hotmail.com");
        miembro = new Miembro(usuario, Rol.MIEMBRO);
        miembro.setTiempoConfigurado(cuandoSucede);
        miembro.setMedioConfigurado(enviarMail);

        String notificacion = "Sugerencia de revision de incidente";

        Comunidad comunidad = new Comunidad();
        comunidad.agregarMiembro(miembro);
        comunidad.generarIncidente(banioCampus, "Estan arreglando el baño del primer piso");
        comunidad.generarIncidente(ascensorMedrano, "Murio el ascensor >:(");
        miembro.agregarComunidad(comunidad);

        try{
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        usuario.setLocalizacion("Buenos Aires", "comuna 8", "Mozart 2300");

        try{
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
*/
