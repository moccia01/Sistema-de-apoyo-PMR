package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.services.twilio.ServicioTwilio;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MensajeWhatsApp implements MedioConfigurado {

    @Override
    public void enviarNotificacion(Miembro miembro, String notificacion) {
        ServicioTwilio servicioTwilio = new ServicioTwilio();
        servicioTwilio.enviarMensaje(miembro.getUsuario().getNumero(), notificacion);
    }
}
