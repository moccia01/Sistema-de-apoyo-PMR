package domain.models.entities.mensajes.Configuraciones;
import domain.models.entities.comunidad.Usuario;
import domain.models.entities.mensajes.MailSender;
import lombok.Setter;

@Setter
public class MensajeEmail implements MedioConfigurado {

    private final String asunto = "Notificacion Comunidad";
    private MailSender mailSender;

    public String nombre(){
        return "MensajeEmail" ;
    }
    public MensajeEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MensajeEmail() {
    }

    public void enviarNotificacion(Usuario usuario, String notificacion) {
        this.mailSender.enviarMensaje(usuario, this.asunto, notificacion);
    }
}

