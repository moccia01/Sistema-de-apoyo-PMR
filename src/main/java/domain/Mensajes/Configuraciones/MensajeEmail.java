package domain.Mensajes.Configuraciones;
import domain.Mensajes.MailSender;
import domain.comunidad.Miembro;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MensajeEmail implements MedioConfigurado {

    private final String asunto = "Notificacion Comunidad";
    private MailSender mailSender;

    public MensajeEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarNotificacion(Miembro miembro, String notificacion) {
        this.mailSender.enviarMensaje(miembro, this.asunto, notificacion);
    }
}

