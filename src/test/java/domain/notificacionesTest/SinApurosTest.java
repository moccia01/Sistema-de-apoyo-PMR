/*
package domain.notificacionesTest;


import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import domain.Mensajes.Configuraciones.MensajeEmail;
import domain.Mensajes.Configuraciones.SinApuro.Prueba;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SinApurosTest {
    @Test
    public void testPrueba() {
        // Crear un miembro de ejemplo
        Miembro miembro = new Miembro();
        Usuario usuario = new Usuario();
        usuario.setMail("facundosu26@gmail.com");
        miembro.setUsuario(usuario);

        // Crear una instancia de Prueba con el horario y la notificación de ejemplo
        LocalTime horario = LocalTime.of(22, 25);
        String notificacion = "¡Hola! Esta es una notificación de prueba.";
        Prueba prueba = new Prueba(horario, miembro);
        prueba.recibirNotificacion(miembro, notificacion);
        try {
            Thread.sleep(60000); // Esperar 1 minuto (ajusta el tiempo según tus necesidades)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verificar si se envió el correo electrónico exitosamente
        System.out.println("El correo ha sido enviado exitosamente.");
    }

    @Test
    public  void nose(){
        LocalTime horario = LocalTime.of(22, 14);
        LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

        System.out.println(horaActual.equals(horario));
        System.out.println(horario);
    }
}
 */