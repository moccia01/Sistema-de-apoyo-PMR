package domain.models.entities.mensajes;

import domain.models.entities.comunidad.Usuario;

public interface MailSender {

    void enviarMensaje(Usuario usuario, String asunto, String notificacion);
}
