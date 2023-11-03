package domain.models.entities.mensajes.Configuraciones;

import domain.models.entities.comunidad.Usuario;

public interface MedioConfigurado {
    void enviarNotificacion(Usuario usuario, String notificaicon);

    String nombre();
}
