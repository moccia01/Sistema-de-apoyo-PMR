package domain.testEntrega3;

import domain.Mensajes.Configuraciones.*;
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

    //@InjectMocks
    private Miembro miembro;
    //@InjectMocks
    private Usuario usuario;
    @InjectMocks
    Usuario user= new Usuario();
    @InjectMocks
    Miembro nisman = new Miembro(user, Rol.MIEMBRO);


    @BeforeEach
    public void init(){
        usuario = new Usuario();
        miembro = new Miembro(usuario, Rol.MIEMBRO);
    }

    @Test
    public void seEnviaUnMailMockeado(){        //TODO Falta que funcione el mockeo
        MedioConfigurado emailConfigurado = Mockito.mock(MedioConfigurado.class);
        // user = new Usuario();
        // nisman = new Miembro(user, Rol.MIEMBRO);
        nisman.setMedioConfigurado(emailConfigurado);
        nisman.getUsuario().setMail("federico21433@hotmail.com");

        Mockito.verify(emailConfigurado, Mockito.only()).enviarNotificacion(nisman, "hola");
    }

    @Test
    public void seEnviaUnMail(){
        MedioConfigurado email = new MensajeEmail(new ServicioMail());
        miembro.setMedioConfigurado(email);
        miembro.getUsuario().setMail("federico21433@hotmail.com");

        email.enviarNotificacion(miembro, "Funcó esto");
    }

    @Test
    public void seGeneraUnIncidenteYSeNotifica(){
        Entidad subteB = new Entidad();
        Establecimiento medrano = new Establecimiento();
        Servicio escaleraMecanicaMedrano = new Servicio();
        Interes interes = new Interes();
        List<Entidad> entidades = new ArrayList<>();
        entidades.add(subteB);
        List<Servicio> servicios = new ArrayList<>();
        servicios.add(escaleraMecanicaMedrano);
        interes.setEntidades(entidades);
        interes.setServicios(servicios);

        CuandoSucede tiempoConfigurado = new CuandoSucede();

        PrestacionDeServicio prestacionDeMedrano = new PrestacionDeServicio();
        prestacionDeMedrano.setEntidad(subteB);
        prestacionDeMedrano.setServicio(escaleraMecanicaMedrano);
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

    @Test
    public void notificarCuandoCierraUnIncidente(){
        MensajeEmail enviarMail = new MensajeEmail();
        CuandoSucede cuandoSucede = new CuandoSucede();

        Usuario usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");
        Miembro miembro = new Miembro(usuario, Rol.MIEMBRO);
        miembro.setTiempoConfigurado(cuandoSucede);
        miembro.setMedioConfigurado(enviarMail);

        Comunidad comunidad = new Comunidad();
        comunidad.agregarMiembro(miembro);

        Establecimiento campus = new Establecimiento();
        campus.setLocalizacion("Buenos Aires", "comuna 8", "Mozart 2300");
        PrestacionDeServicio banioCampus = new PrestacionDeServicio();
        banioCampus.setEstablecimiento(campus);
        comunidad.generarIncidente(banioCampus, "Estan arreglando el baño del primer piso");

        miembro.cerrarIncidente(comunidad, comunidad.getIncidentes().get(0));
        Assertions.assertTrue(comunidad.getIncidentes().get(0).getEstado());
    }
 */
}
