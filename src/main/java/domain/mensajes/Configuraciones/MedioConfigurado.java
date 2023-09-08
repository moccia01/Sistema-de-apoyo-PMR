package domain.mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

public interface MedioConfigurado {
    void enviarNotificacion(Usuario usuario, String notificaicon);
}
