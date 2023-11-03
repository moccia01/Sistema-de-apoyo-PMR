package domain.models.entities.mensajes.Notificaciones;

import domain.models.entities.comunidad.Incidente;
import domain.models.entities.comunidad.Usuario;

public abstract class TipoNotificacion {

    public void notificar(Usuario usuario, Incidente incidente){
        usuario.getTiempoConfigurado().recibirNotificacion(usuario, this.armarNotificacion(incidente));
    }

    public abstract String armarNotificacion(Incidente incidente);
}
