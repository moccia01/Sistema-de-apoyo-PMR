package domain.mensajes.Configuraciones;

import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

import javax.persistence.Entity;


public interface TiempoConfigurado {
    public void recibirNotificacion(Usuario usuario, String notificacion);

    void mandarPendientes(Usuario usuario);
}
