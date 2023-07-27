package domain.Mensajes.Notificaciones;

import domain.comunidad.Incidente;
import domain.comunidad.Miembro;

public class AperturaIncidente extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Hay un nuevo incidente en " + incidente.armarNotificacion();

    }
}
