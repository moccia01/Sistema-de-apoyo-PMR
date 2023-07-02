package domain.notificacionesTest;

import domain.Mensajes.Configuraciones.MensajeEmail;
import domain.Mensajes.Configuraciones.MensajeWhatsApp;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;
import org.junit.jupiter.api.Test;

public class NotificacionesTest {

    @Test
    public void enviarMail(){
        MensajeEmail enviarMail = new MensajeEmail();
        Usuario usuario = new Usuario();
        usuario.setMail("facundosu26@gmail.com");

        Miembro miembro = new Miembro();
        miembro.setUsuario(usuario);

        String mensaje = "buenas este es un notificacion";

        enviarMail.enviarNotificacion(miembro,mensaje);
    }

    @Test
    public void enviarWhatsApp(){
        MensajeWhatsApp msjWhatsapp = new MensajeWhatsApp();
        msjWhatsapp.setNumero("");

        Miembro miembro = new Miembro();

        String mensaje = "Notificacion";
        //TODO mockear esto
        msjWhatsapp.enviarNotificacion(miembro, mensaje);
    }
}
