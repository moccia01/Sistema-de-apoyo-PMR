package domain.mensajes;

import domain.comunidad.Miembro;

public interface MailSender {

    void enviarMensaje(Miembro miembro, String asunto, String notificacion);
}
