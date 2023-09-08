package domain.mensajes;

import domain.comunidad.Usuario;

public interface MailSender {

    void enviarMensaje(Usuario usuario, String asunto, String notificacion);
}
