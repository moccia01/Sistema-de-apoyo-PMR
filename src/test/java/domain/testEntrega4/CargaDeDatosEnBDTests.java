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
import domain.rankings.GeneradorDeRankings;
import domain.repositorios.RepositorioComunidades;
import domain.repositorios.RepositorioIncidentes;
import domain.repositorios.RepositorioUsuarios;
import domain.validaciones.CredencialDeAcceso;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityTransaction;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class CargaDeDatosEnBDTests implements SimplePersistenceTest {

    private Miembro miembro;
    private Miembro elFede;
    private Usuario fede;

    private CredencialDeAcceso credencialFede;

    private Entidad lineaMitre;
    private Servicio escaleraMecanica;
    private Establecimiento estacionRetiro;
    private Establecimiento estacionVillaBallester;
    private PrestacionDeServicio trenesArgentinos;
    private Comunidad comunidadHipoacusicosCABA;
    private Incidente incidente1;
    private Entidad utn;
    private Servicio banio;
    private Servicio escalera;
    private Interes interes;
    private GradoDeConfianza gradoDeConfianza;

    @BeforeEach
    public void init(){
        credencialFede = new CredencialDeAcceso("elFede");



        //servicio
        escaleraMecanica = new Servicio();
        escaleraMecanica.setNombre("Escalera Mecanica");
        escaleraMecanica.setEstado(true);

        estacionRetiro = new Establecimiento();
        estacionRetiro.setNombre("Estacion retiro");
        estacionRetiro.setLocalizacion("Buenos Aires", "Comuna 1", "Av. Dr. Jose M Ramos Mejia 1430");
        estacionRetiro.agregarServicios(escaleraMecanica);


        estacionVillaBallester = new Establecimiento();
        estacionVillaBallester.setNombre("Estacion Villa Ballester");
        estacionVillaBallester.setLocalizacion("Buenos Aires", "San Martin", "San Martin 4900");
        estacionVillaBallester.agregarServicios(escaleraMecanica);


        lineaMitre = new Entidad();
        lineaMitre.setNombre("Linea Mitre");
        lineaMitre.setLocalizacion("Buenos Aires");
        lineaMitre.agregarEstablecimientos(estacionRetiro, estacionVillaBallester);

        trenesArgentinos = new PrestacionDeServicio();
        trenesArgentinos.setEntidad(lineaMitre);
        trenesArgentinos.setServicio(escaleraMecanica);
        trenesArgentinos.setEstablecimiento(estacionVillaBallester);

        comunidadHipoacusicosCABA = new Comunidad();
        comunidadHipoacusicosCABA.generarIncidente(trenesArgentinos, "");

        incidente1 = new Incidente("Se rompio la barrera", trenesArgentinos);
        LocalDateTime fechaAperturaIncidente1 = LocalDateTime.of(2023, 3, 7, 9, 24);
        LocalDateTime fechaCierreIncidente1 = LocalDateTime.of(2023, 3, 9, 10, 54);
        incidente1.setFechaHoraApertura(fechaAperturaIncidente1);
        incidente1.setFechaHoraCierre(fechaCierreIncidente1);


        utn = new Entidad();
        utn.setNombre("UTN");

        escalera = new Servicio();
        escalera.setNombre("Escalera");

        InteresBuilder interesBuilder = new InteresBuilder();
        interes = interesBuilder.agregarEntidades(utn).agregarServicios(escalera, banio).construir();

        gradoDeConfianza = new GradoDeConfianza();
        gradoDeConfianza.setNombreGradoConfianza(NombreGradoConfianza.CONFIABLE_NIVEL_2);


        fede = new Usuario();
        fede.setMail("federico21433@hotmail.com");
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);


        fede.setMedioConfigurado(medio);

        fede.agregarMiembros(elFede);
        fede.setInteres(interes);
        fede.setCredencialDeAcceso(credencialFede);
        fede.setMedioConfigurado(new MensajeEmail());
        fede.setPuntosDeConfianza(5);

        fede.setGradoDeConfianza(gradoDeConfianza);


    }

    @Test
    public void cargarUnosDatos(){
            withTransaction(() -> {
                entityManager().persist(fede);
            });
    }
}
