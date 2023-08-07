package domain.testEntrega3;

import domain.Mensajes.Configuraciones.*;
import domain.Mensajes.MailSender;
import domain.Mensajes.Notificaciones.AperturaIncidente;
import domain.Mensajes.WhatsAppSender;
import domain.builders.EstablecimientoBuilder;
import domain.builders.InteresBuilder;
import domain.builders.PrestacionDeServicioBuilder;
import domain.comunidad.*;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificacionesTest {

    private Miembro miembro;
    private Usuario usuario;
    private Comunidad comunidad;
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
        comunidad = new Comunidad();

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
    }

    @Test
    public void seEnviaUnMailCuandoHayGeneracionDeIncidente() {
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);
        miembro.setMedioConfigurado(medio);
        miembro.setTiempoConfigurado(new CuandoSucede());
        usuario.setLocalizacion("Buenos Aires", "Comuna 5", "Medrano 800");

        comunidad.generarIncidente(escaleraMedrano, "Se rompió la baranda");

        Mockito.verify(mailer, Mockito.only()).enviarMensaje(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void seEnviaUnWhatsAppCuandoHayGeneracionDeIncidente() {
        WhatsAppSender whatsapper = Mockito.mock(WhatsAppSender.class);
        MensajeWhatsApp medio = new MensajeWhatsApp(whatsapper);
        miembro.setMedioConfigurado(medio);
        miembro.setTiempoConfigurado(new CuandoSucede());
        usuario.setLocalizacion("Buenos Aires", "Comuna 5", "Medrano 800");

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
}

/*    @Test
    public void seGeneraUnIncidenteYSeNotifica(){
        Entidad subteB = new Entidad();
        subteB.setNombre("Subte B");
        Interes interes = new Interes();
        interes.agregarEntidades(subteB);
        interes.agregarServicios(escalera);

        CuandoSucede tiempoConfigurado = new CuandoSucede();

        PrestacionDeServicio prestacionDeMedrano = new PrestacionDeServicio();
        prestacionDeMedrano.setEntidad(subteB);
        prestacionDeMedrano.setServicio(escalera);
        prestacionDeMedrano.setEstablecimiento(medrano);

        MedioConfigurado email = new MensajeEmail(new ServicioMail());
        Comunidad campeonesDoMundo = new Comunidad();
        Comunidad operativosEnjoyers = new Comunidad();
        Miembro sentey = new Miembro(new Usuario(), Rol.MIEMBRO);
        sentey.setMedioConfigurado(new MensajeEmail(new ServicioMail()));
        sentey.getUsuario().setMail("federico21433@hotmail.com");
        miembro.setMedioConfigurado(new MensajeEmail(new ServicioMail()));
        miembro.getUsuario().setMail("federico21433@hotmail.com");
        miembro.setInteres(interes);
        miembro.setTiempoConfigurado(tiempoConfigurado);    //TODO Arreglar localizacion en el test para que no devuelva null xd
        //miembro.getUsuario().setLocalizacion("Buenos Aires", "Ciudad Autónoma de Buenos Aires", "AV. MEDRANO 700");
        //sentey.getUsuario().setLocalizacion("Buenos Aires", "Ciudad Autónoma de Buenos Aires", "FLORIDA 2950");
        sentey.setInteres(interes);
        sentey.setTiempoConfigurado(tiempoConfigurado);
        campeonesDoMundo.agregarMiembro(miembro);
        campeonesDoMundo.agregarMiembro(sentey);
        operativosEnjoyers.agregarMiembro(miembro);

        miembro.agregarComunidad(campeonesDoMundo);
        miembro.agregarComunidad(operativosEnjoyers);
        miembro.generarIncidente(prestacionDeMedrano, "Cortocircuito en la escalera mecanica");

    }*/
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

    @Test
    public void recibirNotificacionDebeAgregarNotificacionPendiente() throws InterruptedException {
        LocalTime horario = LocalTime.of(0, 11);
        MensajeEmail enviarMail = new MensajeEmail();

        SinApuros sinApuros = new SinApuros(horario);
        CuandoSucede cuandoSucede = new CuandoSucede();

        Usuario usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");
        Miembro miembro = new Miembro(usuario, Rol.MIEMBRO);
        miembro.setTiempoConfigurado(sinApuros);
        miembro.setMedioConfigurado(enviarMail);

        //segundo usuario
        Usuario usuario1 = new Usuario();
        usuario1.setMail("federico21433@hotmail.com");
        Miembro miembro1 = new Miembro(usuario1,Rol.ADMINISTRADOR);
        miembro1.setTiempoConfigurado(cuandoSucede);
        miembro1.setMedioConfigurado(enviarMail);

        String notificacion = "Mensaje de prueba";

        Comunidad comunidad = new Comunidad();
        comunidad.agregarMiembro(miembro);
        comunidad.agregarMiembro(miembro1);

        comunidad.notificarMiembros(notificacion);
        TimeUnit.SECONDS.sleep(60);
    }

*/
