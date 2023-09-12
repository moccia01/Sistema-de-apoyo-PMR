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
    private Usuario fede;

    private CredencialDeAcceso credencialFede;

    @BeforeEach
    public void init(){
        fede = new Usuario();
        fede.setMail("federico21433@hotmail.com");
        elFede.setRolTemporal(RolTemporal.AFECTADO);
        fede.agregarMiembros(elFede);

        credencialFede = new CredencialDeAcceso("elFede");
        fede.setCredencialDeAcceso(credencialFede);
        MailSender mailer = Mockito.mock(MailSender.class);
        MensajeEmail medio = new MensajeEmail(mailer);
        fede.setMedioConfigurado(medio);
    }

    @Test
    public void cargarUnosDatos(){
            withTransaction(() -> {
                entityManager().persist(fede);
            });
    }
}
