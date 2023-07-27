package domain.Mensajes.Notificaciones;

import domain.comunidad.Incidente;
import domain.comunidad.Miembro;

public abstract class TipoNotificacion {

    public void notificar(Miembro miembro, Incidente incidente){
        miembro.getTiempoConfigurado().recibirNotificacion(miembro, this.armarNotificacion(incidente));
    }

    public abstract String armarNotificacion(Incidente incidente);
}
