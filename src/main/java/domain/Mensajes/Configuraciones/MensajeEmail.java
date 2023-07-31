package domain.Mensajes.Configuraciones;
import domain.Mensajes.MailSender;
import domain.comunidad.Miembro;
import lombok.Setter;

@Setter
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

