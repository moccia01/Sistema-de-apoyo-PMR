package domain.models.entities.mensajes.Configuraciones;

import domain.models.entities.comunidad.Usuario;
import domain.models.entities.mensajes.WhatsAppSender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MensajeWhatsApp implements MedioConfigurado {
    private WhatsAppSender whatsAppSender;

    public MensajeWhatsApp(WhatsAppSender whatsAppSender) {
        this.whatsAppSender = whatsAppSender;
    }

    public MensajeWhatsApp() {
    }

    @Override
    public void enviarNotificacion(Usuario usuario, String notificacion) {
        this.whatsAppSender.enviarMensaje(usuario.getTelefono(), notificacion);
    }
}
