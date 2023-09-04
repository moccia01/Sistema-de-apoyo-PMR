package domain.mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.mensajes.WhatsAppSender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MensajeWhatsApp implements MedioConfigurado {
    private WhatsAppSender whatsAppSender;

    public MensajeWhatsApp(WhatsAppSender whatsAppSender) {
        this.whatsAppSender = whatsAppSender;
    }

    @Override
    public void enviarNotificacion(Miembro miembro, String notificacion) {
        this.whatsAppSender.enviarMensaje(miembro.getUsuario().getTelefono(), notificacion);
    }
}
