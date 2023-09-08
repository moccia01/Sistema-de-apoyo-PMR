package domain.mensajes.Notificaciones;

import domain.comunidad.Incidente;
import domain.comunidad.Miembro;
import domain.comunidad.Usuario;

public abstract class TipoNotificacion {

    public void notificar(Usuario usuario, Incidente incidente){
        usuario.getTiempoConfigurado().recibirNotificacion(usuario, this.armarNotificacion(incidente));
    }

    public abstract String armarNotificacion(Incidente incidente);
}
