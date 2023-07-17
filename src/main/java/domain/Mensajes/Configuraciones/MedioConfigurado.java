package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;

public interface MedioConfigurado {
    void enviarNotificacion(Miembro miembro, String notificaicon);
}
