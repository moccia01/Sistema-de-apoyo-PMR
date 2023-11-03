package domain.models.entities.mensajes.Notificaciones;

import domain.models.entities.comunidad.Incidente;

public class AperturaIncidente extends TipoNotificacion{

    @Override
    public String armarNotificacion(Incidente incidente) {
        return "Hay un nuevo incidente en " + incidente.armarNotificacion();

    }
}
