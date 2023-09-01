package domain.mensajes.Configuraciones;

import domain.comunidad.Miembro;

public interface MedioConfigurado {
    void enviarNotificacion(Miembro miembro, String notificaicon);
}
