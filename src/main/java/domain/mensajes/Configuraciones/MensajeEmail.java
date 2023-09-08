package domain.mensajes.Configuraciones;
import domain.comunidad.Usuario;
import domain.mensajes.MailSender;
import domain.comunidad.Miembro;
import lombok.Setter;

@Setter
public class MensajeEmail implements MedioConfigurado {

    private final String asunto = "Notificacion Comunidad";
    private MailSender mailSender;

    public MensajeEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MensajeEmail() {
    }

    public void enviarNotificacion(Usuario usuario, String notificacion) {
        this.mailSender.enviarMensaje(usuario, this.asunto, notificacion);
    }
}

