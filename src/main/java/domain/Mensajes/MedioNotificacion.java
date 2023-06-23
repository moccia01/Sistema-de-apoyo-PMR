package domain.Mensajes;
import domain.comunidad.Miembro;

public interface MedioNotificacion {
    void enviarNotificacion(Miembro miembro, String notificacion);
}
