
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
    public void recibirNotificacion_DebeAgregarNotificacionPendiente() throws InterruptedException {
        // Arrange
        LocalTime horario = LocalTime.of(16, 50); // Establecer el horario deseado
        LocalTime horario2 = LocalTime.of(14, 39); // Establecer el horario deseado
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

        // Act


        Comunidad comunidad = new Comunidad();
        comunidad.agregarMiembro(miembro);
        comunidad.agregarMiembro(miembro1);

        comunidad.notificarMiembros(notificacion);
        comunidad.notificarMiembros(notificacion+'1');
        TimeUnit.SECONDS.sleep(60);
    }


        @Test
        public void calcularTiempoEspera() {
            // Arrange
            LocalTime horarioActual = LocalTime.now();
            LocalTime horarioDeseado = LocalTime.of(14, 30); // Horario deseado



            System.out.println(horarioActual);
            System.out.println(horarioDeseado);
            long resultado = ChronoUnit.MILLIS.between(horarioActual, horarioDeseado)+ 86400000;;
            System.out.println(resultado);
            long tiempoEnMilisegundos = resultado;
            long segundos = tiempoEnMilisegundos / 1000;
            long minutos = segundos / 60;
            long horas = minutos / 60;
            minutos %= 60;

            System.out.println("Horas: " + horas);
            System.out.println("Minutos: " + minutos);

        }


}
