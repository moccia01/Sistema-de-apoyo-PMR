package domain.testEntrega4;

import domain.builders.EstablecimientoBuilder;
import domain.builders.InteresBuilder;
import domain.builders.PrestacionDeServicioBuilder;
import domain.comunidad.*;
import domain.entidadesDeServicio.Entidad;
import domain.entidadesDeServicio.Establecimiento;
import domain.entidadesDeServicio.PrestacionDeServicio;
import domain.entidadesDeServicio.Servicio;
import domain.mensajes.Configuraciones.CuandoSucede;
import domain.mensajes.Configuraciones.MensajeEmail;
import domain.mensajes.MailSender;
import domain.mensajes.NotificacionesPendientesSender;
import domain.repositorios.RepositorioComunidades;
import domain.repositorios.RepositorioUsuarios;
import domain.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityTransaction;

import static org.mockito.Mockito.when;

public class CargaDeDatosEnBDTests implements SimplePersistenceTest {

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

    private RepositorioUsuarios repoUsuarios;

    private CredencialDeAcceso credencialFede;

    @BeforeEach
    public void init(){
        usuario = new Usuario();
        usuario.setMail("federico21433@hotmail.com");
        miembro = new Miembro(usuario, Rol.MIEMBRO, comunidad);
        usuario.agregarMiembros(miembro);

        fede = new Usuario();
        fede.setMail("federico21433@hotmail.com");
        elFede = new Miembro(fede, Rol.ADMINISTRADOR, operativosEnjoyers);
        elFede.setRolTemporal(RolTemporal.AFECTADO);
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

        comunidad.agregarMiembros(miembro);
        usuario.setInteres(interes);

        operativosEnjoyers.agregarMiembros(elFede, elTomas);
        fede.setInteres(interes);
        tomas.setInteres(interes);

        RepositorioComunidades.agregarComunidades(operativosEnjoyers, comunidad);
        repoUsuarios = new RepositorioUsuarios();

        credencialFede = new CredencialDeAcceso("elFede");
        fede.setCredencialDeAcceso(credencialFede);
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);
        fede.setMedioConfigurado(medio);
    }

    @Test
    public void cargarUnosDatos(){
            //when(fede.getMedioConfigurado()).thenReturn("MensajeEmail");
            withTransaction(() -> {
                entityManager().persist(fede);
            });

        Assertions.assertEquals(1, repoUsuarios.obtenerUsuarios().size());
    }
}
