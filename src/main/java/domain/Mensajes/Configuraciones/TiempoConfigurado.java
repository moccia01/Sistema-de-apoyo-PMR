package domain.Mensajes.Configuraciones;

import domain.comunidad.Miembro;

public interface TiempoConfigurado {
    public void recibirNotificacion(Miembro miembro, String notificacion);

    void mandarPendientes(Miembro miembro);
}
