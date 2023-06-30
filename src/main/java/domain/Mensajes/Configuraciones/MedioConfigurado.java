package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;

public interface MedioConfigurado {
    public void enviarNotificacion(Miembro miembro, String notificaicon);
}
