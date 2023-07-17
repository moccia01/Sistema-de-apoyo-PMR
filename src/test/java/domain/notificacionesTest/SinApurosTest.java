
package domain.notificacionesTest;


import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.Mensajes.Configuraciones.CuandoSucede;
import domain.Mensajes.Configuraciones.MensajeEmail;
import domain.Mensajes.Configuraciones.SinApuros;
import domain.comunidad.Comunidad;
import domain.comunidad.Miembro;
import domain.comunidad.Rol;
import domain.comunidad.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SinApurosTest {
    @Test
    public void recibirNotificacionDebeAgregarNotificacionPendiente() throws InterruptedException {
        LocalTime horario = LocalTime.of(20, 01);
        LocalTime horario2 = LocalTime.of(14, 39);
        MensajeEmail enviarMail = new MensajeEmail();

        SinApuros sinApuros = new SinApuros(horario);
        SinApuros sinApuros1 = new SinApuros(horario);
        CuandoSucede cuandoSucede = new CuandoSucede();

        Usuario usuario = new Usuario();
        usuario.setMail("facundosu26@gmail.com");
        Miembro miembro = new Miembro(usuario, Rol.MIEMBRO);
        miembro.setTiempoConfigurado(sinApuros);
        miembro.setMedioConfigurado(enviarMail);

        //segundo usuario
        Usuario usuario1 = new Usuario();
        usuario1.setMail("xdanime2001@gmail.com");
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
}
